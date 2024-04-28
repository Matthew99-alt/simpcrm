package com.crm.controller;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //TODO: переделать ответ контроллера с  Entity на DTO
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    // TODO: у GET запроса не используем ТЕЛО
    // по имени искать - по строке - не оптимально
    // Этот метод работает НЕ по имени, ты просишь на вход целый объект OrderDTO
    @GetMapping("/orderName")
    public List<Order> getOrderByName(@RequestBody OrderDTO orderDTO) {
        return orderService.findByOrderName(orderDTO.getOrderName());
    }

    @GetMapping("/priority")
    // Этот метод работает НЕ по приоритету, ты просишь на вход целый объект OrderDTO
    public List<Order> getOrderByPriority(@RequestBody OrderDTO orderDTO) {
        return orderService.findByPriority(orderDTO.getPriority());
    }


    //TODO: УБРАТЬ ИМЕНА МЕТОДОВ С USER!!!
    // НЕТУ ИХ ТУТ :) НЕТ)

    @PostMapping("/save")
    public OrderDTO saveUser(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
    }

    @PutMapping("/edit")
    public Order editUser(@RequestBody OrderDTO orderDTO) {
        return orderService.editOrder(orderDTO);
    }
}
