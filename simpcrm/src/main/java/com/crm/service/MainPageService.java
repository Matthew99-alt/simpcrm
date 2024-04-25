package com.crm.service;

import com.crm.entity.ITService;
import com.crm.entity.Program;
import com.crm.reposotiry.ITServiceRepository;
import com.crm.reposotiry.ProgramRepository;

import java.util.List;

public class MainPageService {
    public final ITServiceRepository itServiceRepository;

    public final ProgramRepository programRepository;

    public MainPageService(ITServiceRepository itServiceRepository, ProgramRepository programRepository) {
        this.itServiceRepository = itServiceRepository;
        this.programRepository = programRepository;
    }

    //отображение списка услуг и проектов
    public List<ITService> findAllServices() {
        return itServiceRepository.findAll();
    }

    public List<Program> findAllPrograms() {
        return programRepository.findAll();
    }

}
