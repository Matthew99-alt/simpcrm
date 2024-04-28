package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.reposotiry.OrderRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findByOrderName(String orderName) {
        return orderRepository.findByOrderName(orderName);
    }

    public List<Order> findByPriority(Long priority) {
        return orderRepository.findByPriority(priority);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order savedOrder = orderRepository.save(makeOrderFromOrderDTO(orderDTO, new Order()));
        return makeOrderDTOFromOrder(savedOrder);
    }

    public void deleteOrder(OrderDTO orderDTO) {
        orderRepository.deleteById(orderDTO.getId());
    }

    public Order editOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(EntityNotFoundException::new);
        return orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));
    }

    private OrderDTO makeOrderDTOFromOrder(Order savedOrder) {
        OrderDTO responseOrderDto = new OrderDTO();

        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setOrderName(savedOrder.getOrderName());
        responseOrderDto.setPriority(savedOrder.getPriority());
        responseOrderDto.setStatusTitle("Mock Status Title");
        responseOrderDto.setDescription(savedOrder.getDescription());
        responseOrderDto.setComments("Mock Comments");
        responseOrderDto.setClientTitle("Mock Client Title");
        responseOrderDto.setUserTitle("Mock User Title");
        responseOrderDto.setItServicesTitles(new ArrayList<>());
        responseOrderDto.setProgramsTitles(new ArrayList<>());

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setOrderName(orderDTO.getOrderName());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClientId(2L);
        order.setStatusId(2L);
        order.setUserId(2L);
        return order;
    }
}
