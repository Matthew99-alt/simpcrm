package com.crm.service;

import com.crm.dto.AmenitiesDTO;
import com.crm.dto.OrderDTO;
import com.crm.dto.MerchandiseDTO;
import com.crm.entity.Amenities;
import com.crm.entity.Merchandise;
import com.crm.entity.Order;
import com.crm.reposotiry.AmenitiesRepository;
import com.crm.reposotiry.OrderRepository;
import com.crm.reposotiry.MerchandiseRepository;
import com.crm.reposotiry.StatusRepository;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AmenitiesRepository itServicesRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final AmenitiesService amenitiesService;
    private final MerchandiseService merchandiseService;

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            orderDTOList.add(makeOrderDTOFromOrder(order));
        }
        return orderDTOList;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order savedOrder = orderRepository.save(makeOrderFromOrderDTO(orderDTO, new Order()));
        return makeOrderDTOFromOrder(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO editOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(EntityNotFoundException::new);
        orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));
        return orderDTO;
    }

    public OrderDTO findOrderById(Long id) {
        return makeOrderDTOFromOrder(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
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
        responseOrderDto.setUsers(savedOrder.getUserEntity());
        responseOrderDto.setAmenities(getAmenitiesDTO(savedOrder.getAmenities()));
        responseOrderDto.setMerchandises(getMerchandiseDTO(savedOrder.getMerchandises()));
        responseOrderDto.setTotalNumberOfAmenities(savedOrder.getTotalNumberOfAmenities());
        responseOrderDto.setTotalNumberOfMerchandise(savedOrder.getTotalNumberOfMerchandises());
        responseOrderDto.setTotalCostOfOrder(savedOrder.getTotalCost());

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setOrderName(orderDTO.getOrderName());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClient(userRepository.findById(orderDTO.getClient().getId()).orElseThrow(EntityNotFoundException::new));
        order.setStatus(statusRepository.findById(orderDTO.getStatus().getId()).orElseThrow(EntityNotFoundException::new));
        if (orderDTO.getUsers() != null) {
            order.setUserEntity(userRepository.findById(orderDTO.getUsers().getId()).orElseThrow(EntityNotFoundException::new));
        }
        order.setAmenities(getAmenities(orderDTO.getAmenities()));
        order.setMerchandises(getMerchandise(orderDTO.getMerchandises()));
        order.setTotalNumberOfMerchandises(orderDTO.getMerchandises().size());
        order.setTotalNumberOfAmenities(orderDTO.getAmenities().size());
        order.setTotalCost(getAmenitiesPrices(orderDTO.getAmenities())+getMerchandisesPrices(orderDTO.getMerchandises()));
        editMerchandiseNumber(orderDTO.getMerchandises());

        if(orderDTO.getTotalNumberOfMerchandise()+orderDTO.getTotalNumberOfAmenities()>5){
            order.setTotalCost(order.getTotalCost()*0.8);
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

    private double getAmenitiesPrices(List<AmenitiesDTO> amenitiesDTOS){
        double amenitiesPrice = 0L;
        for(int i = 0; i<amenitiesDTOS.size(); i++){
            amenitiesPrice = amenitiesPrice +
                    amenitiesDTOS.get(i).getPrice() * amenitiesDTOS.get(i).getRatio();
        }
        return amenitiesPrice;
    }

    private double getMerchandisesPrices(List<MerchandiseDTO> merchandiseDTOS){
        double merchandisesPrice = 0L;
        for(int i = 0; i<merchandiseDTOS.size(); i++){
            merchandisesPrice = merchandisesPrice +
                    merchandiseDTOS.get(i).getPrice() * merchandiseDTOS.get(i).getRatio();
        }
        return merchandisesPrice;
    }

    private void editMerchandiseNumber(List<MerchandiseDTO> merchandises){
        for(int i=0; i<merchandises.size(); i++){
            MerchandiseDTO merchandiseDTO = merchandises.get(i);
            merchandiseDTO.setNumberInWarehouse(merchandises.get(i).getNumberInWarehouse()-1);
            merchandiseService.editMerchandise(merchandiseDTO);
        }
    }
}