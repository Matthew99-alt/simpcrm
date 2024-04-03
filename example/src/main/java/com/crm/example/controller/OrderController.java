package com.crm.example.controller;

import com.crm.example.entity.dto.OrderDTO;
import com.crm.example.entity.dto.StatusDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.Order;
import com.crm.example.entity.model.Status;
import com.crm.example.entity.model.User;
import com.crm.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> getAllOrder() {
        return orderService.findAllUsers();
    }

    @PostMapping("/save")
    public Order saveUser(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
    }

    @PutMapping("/edit")
    public Order editUser(@RequestBody OrderDTO orderDTO){ return orderService.editOrder(orderDTO);}
}
