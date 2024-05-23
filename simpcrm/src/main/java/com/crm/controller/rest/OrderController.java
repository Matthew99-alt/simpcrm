package com.crm.controller.rest;

import com.crm.dto.OrderDTO;
import com.crm.service.FileStorageService;
import com.crm.service.OrderService;
import com.crm.uploadClass.UploadClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
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
public class OrderController {
    private final OrderService orderService;
    private final FileStorageService fileStorageService;

    public OrderController(OrderService orderService, FileStorageService fileStorageService) {
        this.orderService = orderService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(
            @RequestHeader("userid") String userId,
            @RequestHeader("password") String password
    ) {
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
        //поставил условие и, падла, всё равно выполняет
        // todo: с падлой разберешься дальше сам?)))
        if (fileStorageService.getFileById(id) != null) {
            fileStorageService.deleteFileById(fileStorageService.getFileById(id).getId());
        }
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public OrderDTO editOrder(@RequestPart("orderDTO") String orderDTOStr,
                              @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(orderDTOStr, OrderDTO.class);
        //та же песня
        // todo: переделать исходя из новой логики сохранения заявки
        UploadClass uploadClass = new UploadClass();
        uploadClass.setFile(file);

        uploadClass.setOrderId(orderDTO.getId());
        fileStorageService.editFile(uploadClass);

        return orderService.editOrder(orderDTO);
    }
}
