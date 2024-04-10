package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.reposotiry.OrderRepository;
import org.springframework.stereotype.Service;

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

    public List<Order> findByOrder_name(String order_name) {
        return orderRepository.findByOrderName(order_name);
    }

    public List<Order> findByPriority(Long priority) {
        return orderRepository.findByPriority(priority);
    }

    private Order makeAnOrder(OrderDTO orderDTO, boolean idEnable){
        Order order = new Order();
        if(idEnable){
            order.setId(orderDTO.getId());
        }
        order.setOrder_name(orderDTO.getOrder_name());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClient_id(orderDTO.getClient_id());
        order.setStatus_id(orderDTO.getStatus_id());
        order.setUser_id(orderDTO.getUser_id());

        return order;
    }
    public Order saveOrder(OrderDTO orderDTO) {
        return orderRepository.save(makeAnOrder(orderDTO, false));
    }
    public void deleteOrder(OrderDTO orderDTO) {
        orderRepository.delete(makeAnOrder(orderDTO, true));
    }

    public Order editOrder(OrderDTO orderDTO){
        return orderRepository.save(makeAnOrder(orderDTO, true));
    }
}
