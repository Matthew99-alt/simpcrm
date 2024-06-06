package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.OrderDTO;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.OrderService;
import com.crm.service.SecurityService;
import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(@RequestHeader("login") String login,
                                @RequestHeader("password") String password) {
        return orderService.findAllOrders();
    }

    @LoggingMethod(role = {"admin", "user"})
    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public OrderDTO saveOrder(@RequestHeader("login") String login,
                              @RequestHeader("password") String password,
                              @RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) {
            return orderService.saveOrder(orderDTOStr, file);
    }

    @LoggingMethod(role = {"admin", "user"})
    @DeleteMapping("/delete")
    public void deleteOrder(@RequestHeader("login") String login,
                            @RequestHeader("password") String password,
                            @RequestParam("id") Long id) {
            orderService.deleteOrder(id);
    }

    @LoggingMethod(role = {"admin"})
    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(@RequestHeader("login") String login,
                              @RequestHeader("password") String password,
                              @RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
            return orderService.editOrder(orderDTOStr, file);
    }
}
