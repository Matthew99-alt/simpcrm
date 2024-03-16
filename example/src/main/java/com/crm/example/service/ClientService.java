package com.crm.example.service;

import com.crm.example.model.Client;

import java.util.ArrayList;
import java.util.Arrays;

public class ClientService {
    public ArrayList<Client> getClients(){
        return new ArrayList<Client>(Arrays.asList(new Client("",
                "",
                "",
                11,
                "",
                11,
                "")));//получаем клиентов
    }
}
