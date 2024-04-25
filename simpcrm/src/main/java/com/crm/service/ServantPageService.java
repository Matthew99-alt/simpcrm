package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.entity.*;
import com.crm.reposotiry.*;

import java.util.ArrayList;
import java.util.List;

public class ServantPageService {

    public final UserRepository userRepository;

    public final OrderRepository orderRepository;

    public final StatusRepository statusRepository;

    public final ProgramRepository programRepository;

    public final ITServiceRepository itServiceRepository;

    public final OrderITServiceRepository orderITServiceRepository;

    public final OrderProgramRepository orderProgramRepository;

    public ServantPageService(UserRepository userRepository, OrderRepository orderRepository, StatusRepository statusRepository, ProgramRepository programRepository, ITServiceRepository itServiceRepository, OrderITServiceRepository orderITServiceRepository, OrderProgramRepository orderProgramRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
        this.programRepository = programRepository;
        this.itServiceRepository = itServiceRepository;
        this.orderITServiceRepository = orderITServiceRepository;
        this.orderProgramRepository = orderProgramRepository;
    }

    //функция отображения данных пользователя
    public User showClient(Order order) {
        //отобразить данные пользователя
        return userRepository.findOneById(order.getClient_id());
    }

    //функция отображения заявок ВСЕХ пользователей
    public ArrayList<OrderDTO> showListOfOrders() {
        //отобразить заявки
        List<Order> orders = orderRepository.findAll();
        OrderDTO orderDTO = new OrderDTO();
        ArrayList<OrderDTO> builtOrders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            //отобразить файлы для скачки
            orderBasicInfo(orderDTO, orders.get(i));
            //отобразить услуги по названию
            isServicesTitles(orderDTO);
            //отобразить программы по названию
            itServicesProgramTitles(orderDTO);
            //отобразить статус по названию
            orderDTO.setStatusTitle(statusRepository.findTitleById(orders.get(i).getId()));
            //отобразить ответственного по имени
            orderDTO.setUserTitle(userRepository.findFirstNameById(orders.get(i).getUser_id()));

            builtOrders.add(orderDTO);
        }
        return builtOrders;
    }

    //функция смены статуса
    public void changeTheStatus(Order newOrder) {
        //отобразить имеющиеся статусы
        statusRepository.findAll();
        //редактировать статус по id
        Order order = orderRepository.findOneById(newOrder.getId());
        order.setStatus_id(order.getStatus_id());
    }

    //функция смены ответственного за выполнение заявки
    public void changeTheResponsible(Order newOrder) {
        //смена ответственного за заявку по имени
        Order order = orderRepository.findOneById(newOrder.getId());
        order.setUser_id(order.getUser_id());
    }

    private void orderBasicInfo(OrderDTO orderDTO, Order order) {
        orderDTO.setOrder_name(order.getOrder_name());
        orderDTO.setPriority(order.getPriority());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setComments(order.getComments());
        orderDTO.setId(order.getId());
    }

    private void isServicesTitles(OrderDTO orderDTO) {
        List<Long> itServicesId = orderITServiceRepository.findITServiceIdByOrderId(orderDTO.getId());
        for (int j = 0; j < itServicesId.size(); j++) {
            orderDTO.getITServicesTitles().add(itServiceRepository.findTitleById(itServicesId.get(j)));
        }
    }

    private void itServicesProgramTitles(OrderDTO orderDTO) {
        List<Long> programsId = orderProgramRepository.findProgramIdByOrderId(orderDTO.getId());
        for (int j = 0; j < programsId.size(); j++) {
            orderDTO.getProgramsTitles().add(programRepository.findTitleById(programsId.get(j)));
        }
    }
}
