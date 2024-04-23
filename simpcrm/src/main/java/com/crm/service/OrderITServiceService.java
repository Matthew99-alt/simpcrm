package com.crm.service;

import com.crm.dto.OrderITServiceDTO;
import com.crm.entity.OrderITService;
import com.crm.reposotiry.OrderITServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderITServiceService {
    public OrderITServiceRepository orderITServiceRepository;

    public OrderITServiceService(OrderITServiceRepository orderITServiceRepository) {
        this.orderITServiceRepository = orderITServiceRepository;
    }

    public List<OrderITService> findAllOrderITService() {
        return orderITServiceRepository.findAll();
    }

    private OrderITService makeAnOrderITService(OrderITServiceDTO orderITServiceDTO){
        OrderITService orderITService = new OrderITService();
        orderITService.setITServicesId(orderITServiceDTO.getITServicesId());
        orderITService.setOrderId(orderITServiceDTO.getOrderId());

        return orderITService;
    }
    public OrderITService saveOrderITService(OrderITServiceDTO orderITServiceDTO) {
        return orderITServiceRepository.save(makeAnOrderITService(orderITServiceDTO));
    }
    public void deleteOrderITService(OrderITServiceDTO orderProgramDTO) {
        orderITServiceRepository.delete(makeAnOrderITService(orderProgramDTO));
    }

    public OrderITService editOrderITService(OrderITServiceDTO orderITServiceDTO){
        return orderITServiceRepository.save(makeAnOrderITService(orderITServiceDTO));
    }
}
