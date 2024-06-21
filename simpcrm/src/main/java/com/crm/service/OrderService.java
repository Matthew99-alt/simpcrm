package com.crm.service;

import com.crm.dto.AmenitiesDTO;
import com.crm.dto.FileStorage;
import com.crm.dto.MerchandiseDTO;
import com.crm.dto.OrderDTO;
import com.crm.entity.Amenities;
import com.crm.entity.Merchandise;
import com.crm.entity.Order;
import com.crm.exception.MyEntityNotFoundException;
import com.crm.reposotiry.AmenitiesRepository;
import com.crm.reposotiry.MerchandiseRepository;
import com.crm.reposotiry.OrderRepository;
import com.crm.reposotiry.StatusRepository;
import com.crm.reposotiry.UserRepository;
import com.crm.uploadClass.UploadClass;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private final AmenitiesRepository amenitiesRepository;
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

    public List<OrderDTO> findUserOrders(String login, String password) {
        List<Order> orders = orderRepository.findOrdersByClientLoginAndClientPassword(login, password);
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

            orderDTO = makeOrderDTOFromOrder(savedOrder);
            return orderDTO;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException("Не удалось сохранить заявку", exception);
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        fileStorageService.deleteFileByOrderId(id);
    }

    public OrderDTO editOrder(String orderDTOStr, MultipartFile file) throws IOException {
        try {
            OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);
            Order order = orderRepository.findById(orderDTO.getId())
                    .orElseThrow(() -> new MyEntityNotFoundException("Данный заказ не найден"));
            orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));

            if (file != null) {
                UploadClass uploadClass = new UploadClass();
                try {
                    FileStorage fileStorage = fileStorageService.getFileByOrderId(orderDTO.getId());
                    if (fileStorage != null) {
                        uploadClass.setId(fileStorage.getId());
                    }
                } catch (Exception e) {
                    log.warn("Файл не найден, будет создан новый файл.", e);
                }

                uploadClass.setOrderId(orderDTO.getId());
                uploadClass.setFile(file);

                if (uploadClass.getId() != null) {
                    fileStorageService.editFile(uploadClass);
                } else {
                    fileStorageService.addFile(uploadClass);
                }
            }

            orderDTO = makeOrderDTOFromOrder(order);
            return orderDTO;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException("Не удалось редактировать заявку", exception);
        }
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
        if(orderDTO.getOrderName() != null) {
            order.setOrderName(orderDTO.getOrderName());
        }
        if(orderDTO.getDescription() != null) {
            order.setDescription(orderDTO.getDescription());
        }
        if(orderDTO.getPriority() != null) {
            order.setPriority(orderDTO.getPriority());
        }
        if(orderDTO.getComments() != null) {
            order.setComments(orderDTO.getComments());
        }
        order.setClient(userRepository.findById(orderDTO.getClient().getId())
                .orElseThrow(() -> new MyEntityNotFoundException("Клиент оставивший заявку не найден")));
        if(orderDTO.getStatus() != null) {
            order.setStatus(statusRepository.findById(orderDTO.getStatus().getId())
                    .orElseThrow(() -> new MyEntityNotFoundException("Данный статус не найден")));
        }
        if (orderDTO.getUsers() != null) {
            order.setUser(userRepository.findById(orderDTO.getUsers().getId())
                    .orElseThrow(() -> new MyEntityNotFoundException("Ответственный по заявке не найден")));
        }

        List<Amenities> amenities = orderDTO.getAmenities() != null ? getAmenities(orderDTO.getAmenities()) : new ArrayList<>();
        List<Merchandise> merchandises = orderDTO.getMerchandises() != null ? getMerchandise(orderDTO.getMerchandises()) : new ArrayList<>();

        order.setAmenities(amenities);
        order.setMerchandises(merchandises);
        order.setTotalNumberOfMerchandises(merchandises.size());
        order.setTotalNumberOfAmenities(amenities.size());

        double totalCost = getAmenitiesPrices(orderDTO.getAmenities()) + getMerchandisesPrices(orderDTO.getMerchandises());

        editMerchandiseNumber(orderDTO.getMerchandises());

        if (order.getTotalNumberOfMerchandises() + order.getTotalNumberOfAmenities() >= 5) {
            order.setTotalCost(totalCost * 0.8);
        }else{
            order.setTotalCost(totalCost);
        }
        if (amenities.isEmpty() && !merchandises.isEmpty()) {
            order.setMerchandises(getMerchandise(orderDTO.getMerchandises()));
            order.setTotalNumberOfMerchandises(orderDTO.getMerchandises().size());
        } else if (!amenities.isEmpty() && merchandises.isEmpty()) {
            order.setAmenities(getAmenities(orderDTO.getAmenities()));
            order.setTotalNumberOfAmenities(orderDTO.getAmenities().size());
        } else {
            order.setAmenities(getAmenities(orderDTO.getAmenities()));
            order.setMerchandises(getMerchandise(orderDTO.getMerchandises()));
            order.setTotalNumberOfMerchandises(orderDTO.getMerchandises().size());
            order.setTotalNumberOfAmenities(orderDTO.getAmenities().size());
        }

        editMerchandiseNumber(orderDTO.getMerchandises());

        return order;
    }

    public OrderDTO getOrderById(Long id){
        return makeOrderDTOFromOrder(orderRepository.findById(id).orElseThrow(()->new MyEntityNotFoundException("Заказ не найден")));
    }

    private ArrayList<AmenitiesDTO> getAmenitiesDTO(List<Amenities> amenitiesList) {
        ArrayList<AmenitiesDTO> amenitiesDTOList = new ArrayList<>();
        for (Amenities amenities : amenitiesList) {
            amenitiesDTOList.add(amenitiesService.makeAmenitiesDTO(new AmenitiesDTO(), amenities));
        }
        return amenitiesDTOList;
    }

    private ArrayList<MerchandiseDTO> getMerchandiseDTO(List<Merchandise> merchandiseList) {
        ArrayList<MerchandiseDTO> merchandiseDTOList = new ArrayList<>();
        for (Merchandise merchandise : merchandiseList) {
            merchandiseDTOList.add(merchandiseService.makeAMerchandiseDTO(new MerchandiseDTO(), merchandise));
        }
        return merchandiseDTOList;
    }

    private ArrayList<Amenities> getAmenities(List<AmenitiesDTO> amenitiesDTOList) {
        ArrayList<Amenities> amenitiesList = new ArrayList<>();
        if (amenitiesDTOList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                amenitiesList.add(amenitiesRepository.findById(amenitiesDTO.getId())
                        .orElseThrow(() -> new MyEntityNotFoundException("Услуга не найдена")));
            }
        }
        return amenitiesList;
    }

    private ArrayList<Merchandise> getMerchandise(List<MerchandiseDTO> merchandiseDTOList) {
        ArrayList<Merchandise> merchandiseList = new ArrayList<>();
        if (merchandiseDTOList != null) {
            for (MerchandiseDTO merchandiseDTO : merchandiseDTOList) {
                merchandiseList.add(merchandiseRepository.findById(merchandiseDTO.getId())
                        .orElseThrow(() -> new MyEntityNotFoundException("Товар не найден")));
            }
        }
        return merchandiseList;
    }

    private double getAmenitiesPrices(List<AmenitiesDTO> amenitiesDTOS) {
        double amenitiesPrice = 0L;
        if (amenitiesDTOS != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesDTOS) {
                amenitiesPrice = amenitiesPrice +
                        amenitiesDTO.getPrice() * amenitiesDTO.getRatio();
            }
        }
        return amenitiesPrice;
    }

    private double getMerchandisesPrices(List<MerchandiseDTO> merchandiseDTOS) {
        double merchandisesPrice = 0L;
        if (merchandiseDTOS != null) {
            for (MerchandiseDTO merchandiseDTO : merchandiseDTOS) {
                merchandisesPrice = merchandisesPrice +
                        merchandiseDTO.getPrice() * merchandiseDTO.getRatio();
            }
        }
        return merchandisesPrice;
    }

    private void editMerchandiseNumber(List<MerchandiseDTO> merchandises) {
        if (merchandises != null) {
            for (MerchandiseDTO merchandiseDTO : merchandises) {
                merchandiseDTO.setNumberInWarehouse(merchandiseDTO.getNumberInWarehouse() - 1);
                merchandiseService.editMerchandise(merchandiseDTO);
            }
        }
    }
}