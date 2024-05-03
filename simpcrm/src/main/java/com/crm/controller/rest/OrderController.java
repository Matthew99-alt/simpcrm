package com.crm.controller.rest;

import com.crm.dto.OrderDTO;
import com.crm.service.OrderService;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAllOrders();
    }

    @PostMapping("/save")
    public OrderDTO saveOrder(@ModelAttribute OrderDTO orderDTO) throws IOException {
        return orderService.saveOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/edit")
    public OrderDTO editOrder(@ModelAttribute OrderDTO orderDTO) throws IOException {
        return orderService.editOrder(orderDTO);
    }
}
