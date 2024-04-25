package com.crm.service;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.entity.OrderITService;
import com.crm.entity.OrderProgram;
import com.crm.entity.User;
import com.crm.reposotiry.*;

import java.util.ArrayList;
import java.util.List;

public class ClientPageService {
    public final UserRepository userRepository;
    public final OrderRepository orderRepository;
    public final StatusRepository statusRepository;
    public final ProgramRepository programRepository;
    public final ITServiceRepository itServiceRepository;
    public final OrderITServiceRepository orderITServiceRepository;
    public final OrderProgramRepository orderProgramRepository;

    public ClientPageService(UserRepository userRepository, OrderRepository orderRepository, StatusRepository statusRepository, ProgramRepository programRepository, ITServiceRepository itServiceRepository, OrderITServiceRepository orderITServiceRepository, OrderProgramRepository orderProgramRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
        this.programRepository = programRepository;
        this.itServiceRepository = itServiceRepository;
        this.orderITServiceRepository = orderITServiceRepository;
        this.orderProgramRepository = orderProgramRepository;
    }

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
            orderDTO.setUserTitle(userRepository.findFirstNameById(orders.get(i).getUser_id()));

            builtOrders.add(orderDTO);

        }
        return builtOrders;
    }

    //добавить функцию отображения ответственного по заявке
    public User showUser(Order order) {
        //отобразить данные пользователя
        return userRepository.findOneById(order.getClient_id());
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
        order.setUser_id(order.getUser_id());
    }

    private void orderBasicInfoIn(OrderDTO orderDTO, Order order, boolean idEnable) {
        order.setOrder_name(orderDTO.getOrder_name());
        order.setPriority(orderDTO.getPriority());
        order.setDescription(orderDTO.getDescription());
        order.setComments(orderDTO.getComments());
        if (idEnable) {
            order.setId(orderDTO.getId());
        }
    }

    private void orderBasicInfoOut(OrderDTO orderDTO, Order order, boolean idEnable) {
        orderDTO.setOrder_name(order.getOrder_name());
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
            orderDTO.getITServicesTitles().add(itServiceRepository.findTitleById(itServicesId.get(j)));
        }
    }

    private void orderProgramTitles(OrderDTO orderDTO) {
        List<Long> programsId = orderProgramRepository.findProgramIdByOrderId(orderDTO.getId());
        for (int j = 0; j < programsId.size(); j++) {
            orderDTO.getProgramsTitles().add(programRepository.findTitleById(programsId.get(j)));
        }
    }

    private void itServicesId(OrderDTO orderDTO) {
        List<String> itServicesTitles = orderDTO.getITServicesTitles();
        for (int j = 0; j < itServicesTitles.size(); j++) {
            List<OrderITService> orderITServiceList = orderITServiceRepository.findByTitle(itServicesTitles.get(j));
            for (int l = 0; l <orderITServiceList.size(); j++) {
                OrderITService orderITService = new OrderITService();
                orderITService.setITServicesId(orderITServiceList.get(l).getITServicesId());
                orderITService.setITServicesId(orderDTO.getId());
                orderITServiceRepository.save(orderITService);
            }
        }
    }

    private void orderProgramsId(OrderDTO orderDTO){
        List<String> programsTitles = orderDTO.getProgramsTitles();
        for (int j = 0; j < programsTitles.size(); j++) {
            List<OrderITService> orderProgramList = orderITServiceRepository.findByTitle(programsTitles.get(j));
            for (int l = 0; l <orderProgramList.size(); j++) {
                OrderProgram orderITService = new OrderProgram();
                orderITService.setIdProgram(orderProgramList.get(l).getITServicesId());
                orderITService.setIdOrder(orderDTO.getId());
                orderProgramRepository.save(orderITService);
            }
        }
    }
}
