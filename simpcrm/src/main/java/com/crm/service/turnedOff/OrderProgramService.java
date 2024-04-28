package com.crm.service.turnedOff;

import com.crm.dto.turnedOff.OrderProgramDTO;
import com.crm.entity.turnedOff.OrderProgram;
import com.crm.reposotiry.turnerdOff.OrderProgramRepository;

import java.util.List;

//@Service
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
//        return orderProgramRepository.save(makeAnOrderProgram(orderProgramDTO));
        return null;
    }
    public void deleteOrderProgram(OrderProgramDTO orderProgramDTO) {
//        orderProgramRepository.delete(makeAnOrderProgram(orderProgramDTO));
    }

    public OrderProgram editOrderProgram(OrderProgramDTO orderProgramDTO){
//        return orderProgramRepository.save(makeAnOrderProgram(orderProgramDTO));
        return null;
    }
}
