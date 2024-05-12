package com.crm.controller.html;

import com.crm.client.FileStorageClient;
import com.crm.dto.FileStorage;
import com.crm.dto.OrderDTO;
import com.crm.service.FileStorageService;
import com.crm.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderControllerView {

    private final OrderService orderService;

    private final FileStorageService fileStorageService;

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<OrderDTO> allOrders = orderService.findAllOrders();
        model.addAttribute("orders", allOrders);
        return "order/orders.html";
    }

    @GetMapping("/personalPage/{orderId}")
    public String getOrders(@PathVariable("orderId") Long orderId, Model model) {
        OrderDTO orderDTO = orderService.findOrderById(orderId);
        model.addAttribute("order", orderDTO);
        return "user/personal_order.html";
    }

    @GetMapping("/getFile/{orderId}")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@PathVariable("orderId") Long orderId) throws IOException {
        FileStorage fileStorage = fileStorageService.getFileById(orderId);
        return fileStorageService.downloadFile(fileStorage.getId());
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteFile(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return "order/orders.html";
    }
}
