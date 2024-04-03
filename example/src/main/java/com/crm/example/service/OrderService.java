package com.crm.example.service;

import com.crm.example.entity.Book;
import com.crm.example.entity.dto.BookDTO;
import com.crm.example.entity.dto.OrderDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.ITService;
import com.crm.example.entity.model.Order;
import com.crm.example.entity.model.Program;
import com.crm.example.entity.model.User;
import com.crm.example.reposotory.OrderRepository;
import com.crm.example.reposotory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    public OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllUsers() {
        return orderRepository.findAll();
    }

    public Order saveOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrder_name(orderDTO.getOrder_name());
        order.setDescription(orderDTO.getDescription());
        order.setComments(orderDTO.getComments());
        order.setClient_id(orderDTO.getClient_id());
        order.setStatus_id(orderDTO.getStatus_id());
        order.setUser_id(orderDTO.getUser_id());

        return orderRepository.save(order);
    }
    public void deleteOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrder_name(orderDTO.getOrder_name());
        order.setDescription(orderDTO.getDescription());
        order.setComments(orderDTO.getComments());
        order.setClient_id(orderDTO.getClient_id());
        order.setStatus_id(orderDTO.getStatus_id());
        order.setUser_id(orderDTO.getUser_id());

        orderRepository.delete(order);
    }

    public Order editOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrder_name(orderDTO.getOrder_name());
        order.setDescription(orderDTO.getDescription());
        order.setComments(orderDTO.getComments());
        order.setClient_id(orderDTO.getClient_id());
        order.setStatus_id(orderDTO.getStatus_id());
        order.setUser_id(orderDTO.getUser_id());

        return orderRepository.save(order);
    }
}
