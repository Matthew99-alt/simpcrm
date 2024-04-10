package com.crm.service;

import com.crm.dto.ITServiceDTO;
import com.crm.entity.ITService;
import com.crm.reposotiry.ITServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITServiceService {
    public final ITServiceRepository itServiceRepository;

    public ITServiceService(ITServiceRepository itServiceRepository) {
        this.itServiceRepository = itServiceRepository;
    }

    public List<ITService> findAllUsers() {
        return itServiceRepository.findAll();
    }

    private ITService makeAnITService(ITServiceDTO itServiceDTO, boolean idEnable){
        ITService itService = new ITService();
        if(idEnable){
            itService.setId(itServiceDTO.getId());
        }
        itService.setPrice(itServiceDTO.getPrice());
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        return itService;
    }
    public ITService saveITService(ITServiceDTO itServiceDTO) {
        return itServiceRepository.save(makeAnITService(itServiceDTO, false));
    }
    public void deleteITService(ITServiceDTO itServiceDTO) {

        itServiceRepository.delete(makeAnITService(itServiceDTO, true));
    }

    public ITService editUser(ITServiceDTO itServiceDTO){
        return itServiceRepository.save(makeAnITService(itServiceDTO, true));
    }

}