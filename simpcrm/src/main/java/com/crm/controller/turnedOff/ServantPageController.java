package com.crm.controller.turnedOff;

import com.crm.dto.OrderDTO;
import com.crm.entity.Order;
import com.crm.service.turnedOff.ServantPageService;

import java.util.ArrayList;

public class ServantPageController {
    ServantPageService servantPageService;
    //функция отображения данных пользователя
    public void showClient(Order order){
        //отобразить данные пользователя
        servantPageService.showClient(order);
    }
    //функция отображения заявок ВСЕХ пользователей
    public ArrayList<OrderDTO> showListOfOrders(){
        return servantPageService.showListOfOrders();
    }
    //функция смены статуса
    public void changeTheStatus(Order order){
        //отобразить имеющиеся статусы
        //редактировать статус по id
        servantPageService.changeTheStatus(order);
    }
    //функция смены ответственного за выполнение заявки
    public void changeTheResponsible(Order order){
        //смена ответственного за заявку по имени
        servantPageService.changeTheResponsible(order);
    }
}
