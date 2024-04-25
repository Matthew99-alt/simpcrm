package com.crm.controller;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/order_name")
    public List<Order> getOrderByName(@RequestBody OrderDTO orderDTO) {
        return orderService.findByOrderName(orderDTO.getOrderName());
    }

    @GetMapping("/priority")
    public List<Order> getOrderByPriority(@RequestBody OrderDTO orderDTO) {
        return orderService.findByPriority(orderDTO.getPriority());
    }


    @PostMapping("/save")
    public OrderDTO saveUser(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
    }

    @PutMapping("/edit")
    public Order editUser(@RequestBody OrderDTO orderDTO){ return orderService.editOrder(orderDTO);}
}
