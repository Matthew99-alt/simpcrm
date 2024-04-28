package com.crm.service;

import com.crm.dto.StatusDTO;
import com.crm.entity.Status;
import com.crm.reposotiry.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
    public final StatusRepository statusRepository;

    public List<Status> findAllUsers() {
        return statusRepository.findAll();
    }
    public List<Status> findByStatus(String status){return statusRepository.findByStatus(status);}
    private Status makeAStatus(StatusDTO statusDTO, Status status){
        status.setStatus(statusDTO.getStatus());
        return status;
    }

    public Status saveStatus(StatusDTO statusDTO) {
        Status status = new Status();
        return statusRepository.save(makeAStatus(statusDTO, status));
    }
    public void deleteStatus(StatusDTO statusDTO) {
       statusRepository.deleteById(statusDTO.getId());
    }
    public Status editStatus(StatusDTO statusDTO){
        Status status = new Status();
        status.setId(statusDTO.getId());
        return statusRepository.save(makeAStatus(statusDTO, status));
    }
}
