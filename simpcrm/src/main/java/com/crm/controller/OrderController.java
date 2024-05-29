package com.crm.controller;

import com.crm.dto.OrderDTO;
import com.crm.service.FileStorageService;
import com.crm.service.OrderService;
import com.crm.uploadClass.UploadClass;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@RestController
@RequestMapping("/rest/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAllOrders();
    }

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public OrderDTO saveOrder(@RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) {
        return orderService.saveOrder(orderDTOStr, file);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(@RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return orderService.editOrder(orderDTOStr, file);
    }
}
