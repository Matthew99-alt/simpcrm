package com.crm.service.turnedOff;

import com.crm.dto.StatusDTO;
import com.crm.entity.Status;
import com.crm.reposotiry.turnerdOff.StatusRepository;

import java.util.List;

//@Service
public class StatusService {
    public final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAllUsers() {
        return statusRepository.findAll();
    }
    public List<Status> findByStatus(String status){return statusRepository.findByStatus(status);}
    private Status makeAStatus(StatusDTO statusDTO, boolean idEnable){
        Status status = new Status();
        if (idEnable) {
            status.setId(statusDTO.getId());
        }
        status.setStatus(statusDTO.getStatus());
        return status;
    }

    public Status saveStatus(StatusDTO statusDTO) {
//        return statusRepository.save(makeAStatus(statusDTO, false));
        return null;
    }
    public void deleteStatus(StatusDTO statusDTO) {
       //statusRepository.delete(makeAStatus(statusDTO, true));
    }
    public Status editStatus(StatusDTO statusDTO){
//        return statusRepository.save(makeAStatus(statusDTO, true));
        return null;
    }
}
