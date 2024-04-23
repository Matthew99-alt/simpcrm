package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.dto.OrderProgramDTO;
import com.crm.entity.Order;
import com.crm.entity.OrderProgram;
import com.crm.reposotiry.OrderProgramRepository;
import com.crm.reposotiry.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProgramService {
    public OrderProgramRepository orderProgramRepository;

    public OrderProgramService(OrderProgramRepository orderProgramRepository) {
        this.orderProgramRepository = orderProgramRepository;
    }

    public List<OrderProgram> findAllOrderProgram() {
        return orderProgramRepository.findAll();
    }

    private OrderProgram makeAnOrderProgram(OrderProgramDTO orderProgramDTO){
        OrderProgram orderProgram = new OrderProgram();
        orderProgram.setIdOrder(orderProgramDTO.getIdOrder());
        orderProgram.setIdProgram(orderProgramDTO.getIdProgram());

        return orderProgram;
    }
    public OrderProgram saveOrderProgram(OrderProgramDTO orderProgramDTO) {
        return orderProgramRepository.save(makeAnOrderProgram(orderProgramDTO));
    }
    public void deleteOrderProgram(OrderProgramDTO orderProgramDTO) {
        orderProgramRepository.delete(makeAnOrderProgram(orderProgramDTO));
    }

    public OrderProgram editOrderProgram(OrderProgramDTO orderProgramDTO){
        return orderProgramRepository.save(makeAnOrderProgram(orderProgramDTO));
    }
}
