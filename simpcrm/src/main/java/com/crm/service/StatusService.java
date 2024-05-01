package com.crm.service;

import com.crm.dto.StatusDTO;
import com.crm.entity.Status;
import com.crm.reposotiry.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
    public final StatusRepository statusRepository;

    public List<StatusDTO> findAllStatuses() {
        List<Status> statuses = statusRepository.findAll();
        ArrayList<StatusDTO> statuseDTOS = new ArrayList<>();
        for (int i=0; i<statuses.size(); i++){
            statuseDTOS.add(makeAStatusDTO(new StatusDTO(),statuses.get(i)));
        }
        return statuseDTOS;
    }
    public StatusDTO findByStatus(String status){
        Status originalStatus = statusRepository.findByStatus(status);
        return makeAStatusDTO(new StatusDTO(),originalStatus);
    }
    private StatusDTO makeAStatusDTO(StatusDTO statusDTO, Status status){
        statusDTO.setId(status.getId());
        statusDTO.setStatus(status.getStatus());
        return statusDTO;
    }
    private Status makeAStatus(StatusDTO statusDTO, Status status){
        status.setStatus(statusDTO.getStatus());
        return status;
    }

    public StatusDTO saveStatus(StatusDTO statusDTO) {
        Status status = new Status();
        statusRepository.save(makeAStatus(statusDTO, status));
        return statusDTO;
    }
    public void deleteStatus(Long id) {
       statusRepository.deleteById(id);
    }
    public StatusDTO editStatus(StatusDTO statusDTO){
        Status status = new Status();
        status.setId(statusDTO.getId());
        statusRepository.save(makeAStatus(statusDTO, status));
        return statusDTO;
    }
}
