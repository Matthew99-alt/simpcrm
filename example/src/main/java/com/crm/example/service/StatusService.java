package com.crm.example.service;

import com.crm.example.entity.dto.ProgramDTO;
import com.crm.example.entity.dto.StatusDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.Program;
import com.crm.example.entity.model.Status;
import com.crm.example.entity.model.User;
import com.crm.example.reposotory.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    public final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAllUsers() {
        return statusRepository.findAll();
    }

    public Status saveStatus(StatusDTO statusDTO) {
        Status status = new Status();
        status.setStatus(statusDTO.getStatus());

        return statusRepository.save(status);
    }
    public void deleteStatus(StatusDTO statusDTO) {
        Status status = new Status();
        status.setId(statusDTO.getId());
        status.setStatus(statusDTO.getStatus());

        statusRepository.delete(status);
    }

    public Status editStatus(StatusDTO statusDTO){
        Status status = new Status();
        status.setId(statusDTO.getId());
        status.setStatus(statusDTO.getStatus());

        return statusRepository.save(status);
    }
}
