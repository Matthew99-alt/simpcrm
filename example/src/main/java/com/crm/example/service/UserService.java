package com.crm.example.service;

import com.crm.example.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserService {
    public ArrayList<User> getUsers(){
        return new ArrayList<>(Arrays.asList(new User(
                "",
                "",
                "",
                11,
                "",
                1,
                "")));//получаем пользователей
    }
}
