package com.crm.controller.html;

import com.crm.dto.OrderDTO;
import com.crm.dto.UserDTO;
import com.crm.service.OrderService;
import com.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderControllerView {

    private final OrderService orderService;


    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<OrderDTO> allOrders = orderService.findAllOrders();
        model.addAttribute("orders", allOrders);
        return "order/orders.html";
    }

    @GetMapping("/personalPage/{orderId}")
    public String getOrders(@PathVariable("orderId") Long orderId, Model model) {
        OrderDTO orderDTO = orderService.findById(orderId);
        model.addAttribute("order", orderDTO);
        return "user/personal_order.html";
    }

}
