package com.crm.controller;

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

    private final SecurityService securityService;

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(@RequestHeader("login") String login,
                                @RequestHeader("password") String password) {
        return orderService.findAllOrders();
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return orderService.findAllOrders();
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }
    }

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public OrderDTO saveOrder(@RequestHeader("login") String login,
                              @RequestHeader("password") String password,
                              @RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) {
        if (securityService.checkAdminRole(login, password, "loggingMethod.role()")) {
            return orderService.saveOrder(orderDTOStr, file);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestHeader("login") String login,
                            @RequestHeader("password") String password,
                            @RequestParam("id") Long id) {
        if (securityService.checkAdminRole(login, password, "loggingMethod.role()")) {
            orderService.deleteOrder(id);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(@RequestHeader("login") String login,
                              @RequestHeader("password") String password,
                              @RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        if (securityService.checkAdminRole(login, password, "loggingMethod.role()")) {
            return orderService.editOrder(orderDTOStr, file);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }
}
