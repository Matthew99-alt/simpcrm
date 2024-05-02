package com.crm.controller.rest;

import com.crm.dto.OrderDTO;
import com.crm.service.OrderService;
import java.util.List;

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
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/edit")
    public OrderDTO editOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.editOrder(orderDTO);
    }
}
