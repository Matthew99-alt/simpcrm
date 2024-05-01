package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.entity.ITService;
import com.crm.entity.Order;
import com.crm.entity.Program;
import com.crm.reposotiry.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

    public final ITServiceRepository itServicesRepository;

    public final ProgramRepository programRepository;

    public final StatusRepository statusRepository;

    public final UserRepository userRepository;

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            orderDTOList.add(makeOrderDTOFromOrder(order));
        }
        return orderDTOList;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order savedOrder = orderRepository.save(makeOrderFromOrderDTO(orderDTO, new Order()));
        return makeOrderDTOFromOrder(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO editOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(EntityNotFoundException::new);
        orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));
        return orderDTO;
    }

    private OrderDTO makeOrderDTOFromOrder(Order savedOrder) {
        OrderDTO responseOrderDto = new OrderDTO();

        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setOrderName(savedOrder.getOrderName());
        responseOrderDto.setPriority(savedOrder.getPriority());
        responseOrderDto.setStatusTitle(savedOrder.getStatus().getStatus());
        responseOrderDto.setDescription(savedOrder.getDescription());
        responseOrderDto.setComments(savedOrder.getComments());
        responseOrderDto.setClientFirstName(savedOrder.getClient().getFirstName());
        responseOrderDto.setClientSecondName(savedOrder.getClient().getSecondName());
        responseOrderDto.setClientMiddleName(savedOrder.getClient().getMiddleName());
        responseOrderDto.setUserFirstName(savedOrder.getUser().getFirstName());
        responseOrderDto.setUserSecondName(savedOrder.getUser().getSecondName());
        responseOrderDto.setUserMiddleName(savedOrder.getUser().getMiddleName());
        responseOrderDto.setItServices(getITServicesTitles(savedOrder.getItServices()));
        responseOrderDto.setPrograms(getProgramTitles(savedOrder.getPrograms()));

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setOrderName(orderDTO.getOrderName());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClient(userRepository.findByFirstNameAndSecondNameAndMiddleName(orderDTO.getClientFirstName(), orderDTO.getClientSecondName(), orderDTO.getClientMiddleName()));
        order.setStatus(statusRepository.findByStatus(orderDTO.getStatusTitle()));
        order.setUser(userRepository.findByFirstNameAndSecondNameAndMiddleName(orderDTO.getUserFirstName(),orderDTO.getUserSecondName(),orderDTO.getUserMiddleName()));
        order.setItServices(getITServicesList(orderDTO.getItServices()));
        order.setPrograms(getProgramList(orderDTO.getPrograms()));
        return order;
    }

    private ArrayList<String> getITServicesTitles(List<ITService> itServicesList){
        ArrayList<String> itServicesListWithTitles = new ArrayList<>();
        for (ITService itService : itServicesList) {
            itServicesListWithTitles.add(itService.getTitle());
        }
        return itServicesListWithTitles;
    }
    private ArrayList<ITService> getITServicesList(List<String> itServicesList){
        ArrayList<ITService> itServices = new ArrayList<>();
        for (String s : itServicesList) {
            itServices.add(itServicesRepository.findByTitle(s));
        }
        return itServices;
    }
    private ArrayList<String> getProgramTitles(List<Program> programList){
        ArrayList<String> programListWithTitles = new ArrayList<>();
        for (Program program : programList) {
            programListWithTitles.add(program.getTitle());
        }
        return programListWithTitles;
    }
    private ArrayList<Program> getProgramList(List<String> programList){
        ArrayList<Program> programs = new ArrayList<>();
        for (String s : programList) {
            programs.add(programRepository.findByTitle(s));
        }
        return programs;
    }

}
