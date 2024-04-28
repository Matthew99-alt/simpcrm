package com.crm.service;

import com.crm.dto.ITServiceDTO;
import com.crm.entity.ITService;
import com.crm.entity.User;
import com.crm.reposotiry.ITServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITServiceService {
    public final ITServiceRepository itServiceRepository;

    public List<ITService> findAllServices() {
        return itServiceRepository.findAll();
    }

    public List<ITService> findByTitle(String title) {
        return itServiceRepository.findByTitle(title);
    }

    public List<ITService> findByPrice(Long price) {
        return itServiceRepository.findByPrice(price);
    }

    public List<ITService> priceMaxToMin(Long price) {
        return itServiceRepository.findByPriceGreaterThan(price);
    }

    private ITService makeAnITService(ITServiceDTO itServiceDTO, ITService itService) {
        itService.setPrice(itServiceDTO.getPrice());
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        return itService;
    }

    public ITService saveITService(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        return itServiceRepository.save(makeAnITService(itServiceDTO, itService));
    }

    public void deleteITService(ITServiceDTO itServiceDTO) {
        itServiceRepository.deleteById(itServiceDTO.getId());
    }

    public ITService editUser(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        itService.setId(itServiceDTO.getId());
        return itServiceRepository.save(makeAnITService(itServiceDTO, itService));
    }

}
