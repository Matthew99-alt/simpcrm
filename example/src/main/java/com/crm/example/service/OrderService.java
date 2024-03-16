package com.crm.example.service;

import com.crm.example.model.ITService;
import com.crm.example.model.Order;
import com.crm.example.model.Program;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderService {
    public ArrayList<Order> getOrders(){
        return new ArrayList<Order>(Arrays.asList(new Order(
                "",
                1,
                "",
                new ArrayList<String>(),
                "",new ArrayList<ITService>(),new ArrayList<Program>())));//получаем заявки
    }
}
