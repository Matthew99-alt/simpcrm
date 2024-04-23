package com.crm.controller;

import com.crm.dto.OrderProgramDTO;
import com.crm.entity.OrderProgram;
import com.crm.service.OrderProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/orderProgram")
public class OrderProgramController {

    private final OrderProgramService orderProgramService;

    public OrderProgramController(OrderProgramService orderProgramService) {
        this.orderProgramService = orderProgramService;
    }

    @GetMapping("/all")
    public List<OrderProgram> getAllUsers() {
        return orderProgramService.findAllOrderProgram();
    }

    @PostMapping("/save")
    public OrderProgram saveUser(@RequestBody OrderProgramDTO orderProgramDTO) {
        return orderProgramService.saveOrderProgram(orderProgramDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody OrderProgramDTO orderProgramDTO) {
        orderProgramService.deleteOrderProgram(orderProgramDTO);
    }

    @PutMapping("/edit")
    public OrderProgram editUser(@RequestBody OrderProgramDTO orderProgramDTO){
        return orderProgramService.editOrderProgram(orderProgramDTO);
    }
}
