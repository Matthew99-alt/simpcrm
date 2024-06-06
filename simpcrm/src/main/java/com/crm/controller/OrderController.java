package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.OrderDTO;
import com.crm.service.OrderService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/rest/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password
    ) {
        return orderService.findAllOrders();
    }

    //метод для поиска заявок по клиенту их оформившему
    @LoggingMethod(role = {"admin","user"})
    @GetMapping("/getUserOrders")
    public List<OrderDTO> getUserOrders(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password){
        return orderService.findUserOrders(login, password);
    }

    @LoggingMethod(role = {"admin", "user"})
    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public OrderDTO saveOrder(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestPart("orderDTO") String orderDTOStr,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return orderService.saveOrder(orderDTOStr, file);
    }

    @LoggingMethod(role = {"admin", "user"})
    @DeleteMapping("/delete")
    public void deleteOrder(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam("id") Long id
    ) {
        orderService.deleteOrder(id);
    }

    @LoggingMethod(role = {"admin"})
    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestPart("orderDTO") String orderDTOStr,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        return orderService.editOrder(orderDTOStr, file);
    }
}
