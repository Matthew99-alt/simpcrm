package com.crm.service;

import com.crm.dto.AmenitiesDTO;
import com.crm.dto.FileStorage;
import com.crm.dto.MerchandiseDTO;
import com.crm.dto.OrderDTO;
import com.crm.entity.Amenities;
import com.crm.entity.Merchandise;
import com.crm.entity.Order;
import com.crm.reposotiry.AmenitiesRepository;
import com.crm.reposotiry.MerchandiseRepository;
import com.crm.reposotiry.OrderRepository;
import com.crm.reposotiry.StatusRepository;
import com.crm.reposotiry.UserRepository;
import com.crm.uploadClass.UploadClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final AmenitiesRepository itServicesRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final AmenitiesService amenitiesService;
    private final MerchandiseService merchandiseService;
    private final FileStorageService fileStorageService;

    private final ObjectMapper objectMapper;

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            orderDTOList.add(makeOrderDTOFromOrder(order));
        }
        return orderDTOList;
    }

    public OrderDTO saveOrder(String orderDTOStr, MultipartFile file) {
        try {
            OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);
            Order savedOrder = orderRepository.save(makeOrderFromOrderDTO(orderDTO, new Order()));
            orderDTO.setId(savedOrder.getId());

            if (file != null) {
                UploadClass uploadClass = new UploadClass();
                uploadClass.setFile(file);

                uploadClass.setOrderId(savedOrder.getId());
                fileStorageService.addFile(uploadClass);
            }

            //в order было проведено вычисление итоговой стоимости, количества товаров и услуг.
            //эти параметры не отражены в принятом orderDTO
            //поэтому при сохранении заказа мы переопределяем orderDTO со всеми необходимыми данными
            orderDTO = makeOrderDTOFromOrder(savedOrder);

            return orderDTO;
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        throw new RuntimeException("Не удалось сохранить заявку");
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        fileStorageService.deleteFileByOrderId(id);
    }

    public OrderDTO editOrder(String orderDTOStr, MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);

        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(EntityNotFoundException::new);
        orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));

        if (file != null) {
            UploadClass uploadClass = new UploadClass();
            try {
                FileStorage fileStorage = fileStorageService.getFileByOrderId(orderDTO.getId());
                if (fileStorage != null) {
                    uploadClass.setId(fileStorage.getId());
                }
            } catch (Exception e) {
                // Логирование исключения
                System.out.println("Файл не найден, будет создан новый файл.");
            }

            uploadClass.setOrderId(orderDTO.getId());
            uploadClass.setFile(file);

            if (uploadClass.getId() != null) {
                fileStorageService.editFile(uploadClass);
            } else {
                fileStorageService.addFile(uploadClass);
            }
        }

            //в order было проведено вычисление итоговой стоимости, количества товаров и услуг.
            //эти параметры не отражены в принятом orderDTO
            //поэтому при сохранении заказа мы переопределяем orderDTO со всеми необходимыми данными
            orderDTO = makeOrderDTOFromOrder(order);

        return orderDTO;
    }

    private OrderDTO makeOrderDTOFromOrder(Order savedOrder) {
        OrderDTO responseOrderDto = new OrderDTO();

        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setOrderName(savedOrder.getOrderName());
        responseOrderDto.setPriority(savedOrder.getPriority());
        responseOrderDto.setStatus(savedOrder.getStatus());
        responseOrderDto.setDescription(savedOrder.getDescription());
        responseOrderDto.setComments(savedOrder.getComments());
        responseOrderDto.setClient(savedOrder.getClient());
        responseOrderDto.setUsers(savedOrder.getUser());
        responseOrderDto.setAmenities(getAmenitiesDTO(savedOrder.getAmenities()));
        responseOrderDto.setMerchandises(getMerchandiseDTO(savedOrder.getMerchandises()));
        responseOrderDto.setTotalNumberOfAmenities(savedOrder.getTotalNumberOfAmenities());
        responseOrderDto.setTotalNumberOfMerchandise(savedOrder.getTotalNumberOfMerchandises());
        responseOrderDto.setTotalCostOfOrder(savedOrder.getTotalCost());

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        if(orderDTO.getOrderName()!=null) {
            order.setOrderName(orderDTO.getOrderName());
        }
        if(orderDTO.getDescription()!=null) {
            order.setDescription(orderDTO.getDescription());
        }
        if(orderDTO.getPriority()!=null) {
            order.setPriority(orderDTO.getPriority());
        }
        if(orderDTO.getComments()!=null) {
            order.setComments(orderDTO.getComments());
        }
        order.setClient(userRepository.findById(orderDTO.getClient().getId()).orElseThrow(EntityNotFoundException::new));
        order.setStatus(statusRepository.findById(orderDTO.getStatus().getId()).orElseThrow(EntityNotFoundException::new));
        if (orderDTO.getUsers() != null) {
            order.setUser(userRepository.findById(orderDTO.getUsers().getId()).orElseThrow(EntityNotFoundException::new));
        }
        order.setAmenities(getAmenities(orderDTO.getAmenities()));
        order.setMerchandises(getMerchandise(orderDTO.getMerchandises()));
        order.setTotalNumberOfMerchandises(orderDTO.getMerchandises().size());
        order.setTotalNumberOfAmenities(orderDTO.getAmenities().size());
        order.setTotalCost(
                getAmenitiesPrices(orderDTO.getAmenities()) + getMerchandisesPrices(orderDTO.getMerchandises()));
        editMerchandiseNumber(orderDTO.getMerchandises());

        if (orderDTO.getTotalNumberOfMerchandise() + orderDTO.getTotalNumberOfAmenities() > 5) {
            order.setTotalCost(order.getTotalCost() * 0.8);
        }

        return order;
    }

    private ArrayList<AmenitiesDTO> getAmenitiesDTO(List<Amenities> itServicesList) {
        ArrayList<AmenitiesDTO> itServicesDTOList = new ArrayList<>();
        for (Amenities amenities : itServicesList) {
            itServicesDTOList.add(amenitiesService.makeAmenitiesDTO(new AmenitiesDTO(), amenities));
        }
        return itServicesDTOList;
    }

    private ArrayList<MerchandiseDTO> getMerchandiseDTO(List<Merchandise> merchandiseList) {
        ArrayList<MerchandiseDTO> merchandiseDTOList = new ArrayList<>();
        for (Merchandise merchandise : merchandiseList) {
            merchandiseDTOList.add(merchandiseService.makeAMerchandiseDTO(new MerchandiseDTO(), merchandise));
        }
        return merchandiseDTOList;
    }

    private ArrayList<Amenities> getAmenities(List<AmenitiesDTO> itServicesDTOList) {
        ArrayList<Amenities> itServicesList = new ArrayList<>();
        for (AmenitiesDTO amenitiesDTO : itServicesDTOList) {
            itServicesList.add(itServicesRepository.findById(amenitiesDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return itServicesList;
    }

    private ArrayList<Merchandise> getMerchandise(List<MerchandiseDTO> merchandiseDTOList) {
        ArrayList<Merchandise> merchandiseList = new ArrayList<>();
        for (MerchandiseDTO merchandiseDTO : merchandiseDTOList) {
            merchandiseList.add(merchandiseRepository.findById(merchandiseDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return merchandiseList;
    }

    private double getAmenitiesPrices(List<AmenitiesDTO> amenitiesDTOS) {
        double amenitiesPrice = 0L;
        for (AmenitiesDTO amenitiesDTO : amenitiesDTOS) {
            amenitiesPrice = amenitiesPrice +
                    amenitiesDTO.getPrice() * amenitiesDTO.getRatio();
        }
        return amenitiesPrice;
    }

    private double getMerchandisesPrices(List<MerchandiseDTO> merchandiseDTOS) {
        double merchandisesPrice = 0L;
        for (MerchandiseDTO merchandiseDTO : merchandiseDTOS) {
            merchandisesPrice = merchandisesPrice +
                    merchandiseDTO.getPrice() * merchandiseDTO.getRatio();
        }
        return merchandisesPrice;
    }

    private void editMerchandiseNumber(List<MerchandiseDTO> merchandises) {
        for (MerchandiseDTO merchandiseDTO : merchandises) {
            merchandiseDTO.setNumberInWarehouse(merchandiseDTO.getNumberInWarehouse() - 1);
            merchandiseService.editMerchandise(merchandiseDTO);
        }
    }
}