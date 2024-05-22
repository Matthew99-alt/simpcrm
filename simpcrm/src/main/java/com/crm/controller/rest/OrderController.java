package com.crm.controller.rest;

import com.crm.dto.OrderDTO;
import com.crm.service.FileStorageService;
import com.crm.service.OrderService;

import java.io.IOException;
import java.util.List;

import com.crm.uploadClass.UploadClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/order")
public class OrderController {
    private final OrderService orderService;
    private final FileStorageService fileStorageService;

    public OrderController(OrderService orderService, FileStorageService fileStorageService) {
        this.orderService = orderService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAllOrders();
    }

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public OrderDTO saveOrder(@RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);

        orderService.saveOrder(orderDTO);
        //Как сказать ему не пропустить это если файла нет?
        UploadClass uploadClass = new UploadClass();
        uploadClass.setFile(file);

        uploadClass.setOrderId(orderDTO.getId());
        fileStorageService.addFile(uploadClass);

        return orderDTO;
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        //поставил условие и, падла, всё равно выполняет
        if(fileStorageService.getFileById(id)!=null){
            fileStorageService.deleteFile(fileStorageService.getFileById(id).getId());
        }
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(@RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);
        //та же песня
        UploadClass uploadClass = new UploadClass();
        uploadClass.setFile(file);

        uploadClass.setOrderId(orderDTO.getId());
        fileStorageService.editFile(uploadClass);

        return orderService.editOrder(orderDTO);
    }
}
