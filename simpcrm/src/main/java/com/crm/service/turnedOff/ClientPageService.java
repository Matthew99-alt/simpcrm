package com.crm.service.turnedOff;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.entity.OrderITService;
import com.crm.entity.OrderProgram;
import com.crm.entity.User;
import com.crm.reposotiry.turnerdOff.ITServiceRepository;
import com.crm.reposotiry.turnerdOff.OrderITServiceRepository;
import com.crm.reposotiry.turnerdOff.OrderProgramRepository;
import com.crm.reposotiry.OrderRepository;
import com.crm.reposotiry.turnerdOff.ProgramRepository;
import com.crm.reposotiry.turnerdOff.StatusRepository;
import com.crm.reposotiry.turnerdOff.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientPageService {
    public final UserRepository userRepository;
    public final OrderRepository orderRepository;
    public final StatusRepository statusRepository;
    public final ProgramRepository programRepository;
    public final ITServiceRepository itServiceRepository;
    public final OrderITServiceRepository orderITServiceRepository;
    public final OrderProgramRepository orderProgramRepository;

    //добавить функцию отображения всех заявок ОДНОГО пользователя
    public ArrayList<OrderDTO> usersOrderList(User user) {
        List<Order> orders = orderRepository.findByUserId(user.getId());

        OrderDTO orderDTO = new OrderDTO();

        ArrayList<OrderDTO> builtOrders = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            //отобразить заявки
            orderBasicInfoOut(orderDTO, orders.get(i), true);
            //отобразить файлы для скачки

            //отобразить услуги по названию
            itServicesTitles(orderDTO);

            //отобразить программы по названию
            orderProgramTitles(orderDTO);

            //отобразить статус по названию
            orderDTO.setStatusTitle(statusRepository.findTitleById(orders.get(i).getId()));

            //отобразить ответственного по имени
            orderDTO.setUserTitle(userRepository.findFirstNameById(orders.get(i).getUserId()));

            builtOrders.add(orderDTO);

        }
        return builtOrders;
    }

    //добавить функцию отображения ответственного по заявке
    public User showUser(Order order) {
        //отобразить данные пользователя
        return userRepository.findOneById(order.getClientId());
    }

    //добавление, удаление и редактирование заявки
    public void addOrder(OrderDTO orderDTO) {
        Order order = new Order();

        //Добавление нового заказа (класс order)
        orderBasicInfoIn(orderDTO, order, false);
        //Добавление нового файла (Клиент сервера)

        //Добавление услуги по названию в промежуточные таблицы
        itServicesId(orderDTO);
        //Добавление программы по названию в промежуточные таблицы
        orderProgramsId(orderDTO);
        //Добавление статуса по названию

        //Добавление ответственного по имени

    }

    public void deleteOrder() {
        //удалить заявку
        //удалить файлы
        //удалить услугу назначенную на данную заявку
        //удалить статус удаляемой услуги
        //удалить программы по названию
        //удалить ответственного удаляемой услуги
        //удалить данные в промежуточной таблице услуг
        //удалить данные в промежуточной таблице программ
    }

    public void editOrder() {
        //редактировать заявку
        //редактировать файлы
        //редактировать услугу назначенную на данную заявку
        //редактировать статус удаляемой услуги
        //удалить программы по названию
        //редактировать ответственного удаляемой услуги
        //редактировать данные в промежуточной таблице услуг
        //редактировать данные в промежуточной таблице программ
    }

    //функция смены ответственного за выполнение заявки
    public void changeTheResponsible(Order newOrder) {
        //смена ответственного за заявку по имени
        Order order = orderRepository.findOneById(newOrder.getId());
        order.setUserId(order.getUserId());
    }

    private void orderBasicInfoIn(OrderDTO orderDTO, Order order, boolean idEnable) {
        order.setOrderName(orderDTO.getOrderName());
        order.setPriority(orderDTO.getPriority());
        order.setDescription(orderDTO.getDescription());
        order.setComments(orderDTO.getComments());
        if (idEnable) {
            order.setId(orderDTO.getId());
        }
    }

    private void orderBasicInfoOut(OrderDTO orderDTO, Order order, boolean idEnable) {
        orderDTO.setOrderName(order.getOrderName());
        orderDTO.setPriority(order.getPriority());
        orderDTO.setDescription(order.getDescription());
        orderDTO.setComments(order.getComments());
        if (idEnable) {
            orderDTO.setId(order.getId());
        }
    }

    private void itServicesTitles(OrderDTO orderDTO) {
        List<Long> itServicesId = orderITServiceRepository.findITServiceIdByOrderId(orderDTO.getId());
        for (int j = 0; j < itServicesId.size(); j++) {
            orderDTO.getItServicesTitles().add(itServiceRepository.findTitleById(itServicesId.get(j)));
        }
    }

    private void orderProgramTitles(OrderDTO orderDTO) {
        List<Long> programsId = orderProgramRepository.findProgramIdByOrderId(orderDTO.getId());
        for (int j = 0; j < programsId.size(); j++) {
            orderDTO.getProgramsTitles().add(programRepository.findTitleById(programsId.get(j)));
        }
    }

    private void itServicesId(OrderDTO orderDTO) {
        List<String> itServicesTitles = orderDTO.getItServicesTitles();
        for (int j = 0; j < itServicesTitles.size(); j++) {
            List<OrderITService> orderITServiceList = orderITServiceRepository.findByTitle(itServicesTitles.get(j));
            for (int l = 0; l < orderITServiceList.size(); j++) {
                OrderITService orderITService = new OrderITService();
                orderITService.setItServicesId(orderITServiceList.get(l).getItServicesId());
                orderITService.setItServicesId(orderDTO.getId());
//                orderITServiceRepository.save(orderITService);
            }
        }
    }

    private void orderProgramsId(OrderDTO orderDTO) {
        List<String> programsTitles = orderDTO.getProgramsTitles();
        for (int j = 0; j < programsTitles.size(); j++) {
            List<OrderITService> orderProgramList = orderITServiceRepository.findByTitle(programsTitles.get(j));
            for (int l = 0; l < orderProgramList.size(); j++) {
                OrderProgram orderITService = new OrderProgram();
                orderITService.setIdProgram(orderProgramList.get(l).getItServicesId());
                orderITService.setIdOrder(orderDTO.getId());
//                orderProgramRepository.save(orderITService);
            }
        }
    }
}
