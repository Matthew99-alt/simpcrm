package com.crm.example.service;

import com.crm.example.entity.Book;
import com.crm.example.entity.dto.BookDTO;
import com.crm.example.entity.dto.ITServiceDTO;
import com.crm.example.entity.dto.UserDTO;
import com.crm.example.entity.model.ITService;
import com.crm.example.entity.model.User;
import com.crm.example.reposotory.ITServiceRepository;
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

    public ITService saveITService(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        return itServiceRepository.save(itService);
    }
    public void deleteITService(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        itService.setId(itServiceDTO.getId());
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        itServiceRepository.delete(itService);
    }

    public ITService editUser(ITServiceDTO itServiceDTO){
        ITService itService = new ITService();
        itService.setId(itServiceDTO.getId());
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        return itServiceRepository.save(itService);
    }

}
