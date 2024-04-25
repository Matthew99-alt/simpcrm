package com.crm.controller.turnedOff;

import com.crm.dto.OrderITServiceDTO;
import com.crm.entity.OrderITService;
import com.crm.service.turnedOff.OrderITServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/rest/orderITService")
public class OrderITServiceController {

    private final OrderITServiceService orderITServiceService;

    public OrderITServiceController(OrderITServiceService orderITServiceService) {
        this.orderITServiceService = orderITServiceService;
    }

    @GetMapping("/all")
    public List<OrderITService> getAllUsers() {
        return orderITServiceService.findAllOrderITService();
    }

    @PostMapping("/save")
    public OrderITService saveUser(@RequestBody OrderITServiceDTO orderITServiceDTO) {
        return orderITServiceService.saveOrderITService(orderITServiceDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody OrderITServiceDTO orderITServiceDTO) {
        orderITServiceService.deleteOrderITService(orderITServiceDTO);
    }

    @PutMapping("/edit")
    public OrderITService editUser(@RequestBody OrderITServiceDTO orderITServiceDTO){
        return orderITServiceService.editOrderITService(orderITServiceDTO);
    }
}
