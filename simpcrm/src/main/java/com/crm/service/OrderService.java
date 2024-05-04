package com.crm.service;

import com.crm.dto.ITServiceDTO;
import com.crm.dto.OrderDTO;
import com.crm.dto.ProgramDTO;
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

    public final ITServiceService itServiceService;

    public final ProgramService programService;

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

    public OrderDTO findById(Long id){
        return makeOrderDTOFromOrder(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    private OrderDTO makeOrderDTOFromOrder(Order savedOrder) {
        OrderDTO responseOrderDto = new OrderDTO();

        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setOrderName(savedOrder.getOrderName());
        responseOrderDto.setPriority(savedOrder.getPriority());
        responseOrderDto.setStatus(savedOrder.getStatus());
        responseOrderDto.setDescription(savedOrder.getDescription());
        responseOrderDto.setComments(savedOrder.getComments());
        responseOrderDto.setClient(savedOrder.getClient());
        responseOrderDto.setUsers(savedOrder.getUser());
        responseOrderDto.setItServices(getITServicesDTO(savedOrder.getItServices()));
        responseOrderDto.setPrograms(getProgramDTO(savedOrder.getPrograms()));

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setOrderName(orderDTO.getOrderName());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClient(userRepository.findById(orderDTO.getClient().getId()).orElseThrow(EntityNotFoundException::new));
        order.setStatus(statusRepository.findById(orderDTO.getStatus().getId()).orElseThrow(EntityNotFoundException::new));
        if(orderDTO.getUsers()!=null){
            order.setUser(userRepository.findById(orderDTO.getUsers().getId()).orElseThrow(EntityNotFoundException::new));
        }
        order.setItServices(getITServices(orderDTO.getItServices()));
        order.setPrograms(getProgram(orderDTO.getPrograms()));
        return order;
    }

    private ArrayList<ITServiceDTO> getITServicesDTO(List<ITService> itServicesList){
        ArrayList<ITServiceDTO> itServicesDTOList = new ArrayList<>();
        for (ITService itService : itServicesList) {
            itServicesDTOList.add(itServiceService.makeAnITServiceDTO(new ITServiceDTO(), itService));
        }
        return itServicesDTOList;
    }
    private ArrayList<ProgramDTO> getProgramDTO(List<Program> programList){
        ArrayList<ProgramDTO> programDTOList = new ArrayList<>();
        for (Program program : programList) {
            programDTOList.add(programService.makeAProgramDTO(new ProgramDTO(),program));
        }
        return programDTOList;
    }

    private ArrayList<ITService> getITServices(List<ITServiceDTO> itServicesDTOList){
        ArrayList<ITService> itServicesList = new ArrayList<>();
        for (ITServiceDTO itServiceDTO : itServicesDTOList) {
            itServicesList.add(itServicesRepository.findById(itServiceDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return itServicesList;
    }
    private ArrayList<Program> getProgram(List<ProgramDTO> programDTOList){
        ArrayList<Program> programList = new ArrayList<>();
        for (ProgramDTO programDTO : programDTOList) {
            programList.add(programRepository.findById(programDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return programList;
    }
}