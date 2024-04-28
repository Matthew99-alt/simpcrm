package com.crm.service.turnedOff;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.entity.User;
import com.crm.reposotiry.ITServiceRepository;
import com.crm.reposotiry.turnerdOff.OrderITServiceRepository;
import com.crm.reposotiry.turnerdOff.OrderProgramRepository;
import com.crm.reposotiry.OrderRepository;
import com.crm.reposotiry.ProgramRepository;
import com.crm.reposotiry.StatusRepository;
import com.crm.reposotiry.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServantPageService {

    public final UserRepository userRepository;

    public final OrderRepository orderRepository;

    public final StatusRepository statusRepository;

    public final ProgramRepository programRepository;

    public final ITServiceRepository itServiceRepository;

    public final OrderITServiceRepository orderITServiceRepository;

    public final OrderProgramRepository orderProgramRepository;

    //функция отображения данных пользователя
    public User showClient(Order order) {
        //отобразить данные пользователя
        return userRepository.findOneById(order.getClientId());
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
            orderDTO.setUserTitle(userRepository.findFirstNameById(orders.get(i).getUserId()));

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
        order.setStatusId(order.getStatusId());
    }

    //функция смены ответственного за выполнение заявки
    public void changeTheResponsible(Order newOrder) {
        //смена ответственного за заявку по имени
        Order order = orderRepository.findOneById(newOrder.getId());
        order.setUserId(order.getUserId());
    }

    private void orderBasicInfo(OrderDTO orderDTO, Order order) {
        orderDTO.setOrderName(order.getOrderName());
        orderDTO.setPriority(order.getPriority());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setComments(order.getComments());
        orderDTO.setId(order.getId());
    }

    private void isServicesTitles(OrderDTO orderDTO) {
        List<Long> itServicesId = orderITServiceRepository.findITServiceIdByOrderId(orderDTO.getId());
        for (int j = 0; j < itServicesId.size(); j++) {
            orderDTO.getItServicesTitles().add(itServiceRepository.findTitleById(itServicesId.get(j)));
        }
    }

    private void itServicesProgramTitles(OrderDTO orderDTO) {
        List<Long> programsId = orderProgramRepository.findProgramIdByOrderId(orderDTO.getId());
        for (int j = 0; j < programsId.size(); j++) {
            orderDTO.getProgramsTitles().add(programRepository.findTitleById(programsId.get(j)));
        }
    }
}
