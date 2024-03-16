package com.crm.example.service;

import com.crm.example.model.Program;

public class ProgramService {
    public Program getProgram(){
        return new Program("","",11);//получаем программы
    }
}
