
package com.crm.service;

import com.crm.dto.ITServiceDTO;
import com.crm.entity.ITService;
import com.crm.entity.User;
import com.crm.reposotiry.ITServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ITServiceService {
    public final ITServiceRepository itServiceRepository;

    public List<ITServiceDTO> findAllServices() {
        List<ITService> itServices = itServiceRepository.findAll();
        ArrayList<ITServiceDTO> itServiceDTOS = new ArrayList<>();
        for (ITService itService : itServices) {
            itServiceDTOS.add(makeAnITServiceDTO(new ITServiceDTO(), itService));
        }
        return itServiceDTOS;
    }

    public ITServiceDTO findByTitle(String title) {
        return makeAnITServiceDTO(new ITServiceDTO(),itServiceRepository.findByTitle(title));
    }

    public ITServiceDTO findById(Long id){
        return makeAnITServiceDTO(new ITServiceDTO(),itServiceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public List<ITServiceDTO> findByPrice(Long price) {
        List<ITService> itServices = itServiceRepository.findByPrice(price);
        ArrayList<ITServiceDTO> itServiceDTOS = new ArrayList<>();
        for(int i=0; i<itServices.size(); i++){
            itServiceDTOS.add(makeAnITServiceDTO(new ITServiceDTO(), itServices.get(i)));
        }
        return itServiceDTOS;
    }

    public List<ITServiceDTO> priceMaxToMin(Long price) {
        List<ITService> itServices =  itServiceRepository.findByPriceGreaterThan(price);
        ArrayList<ITServiceDTO> itServiceDTOS = new ArrayList<>();
        for(int i=0; i<itServices.size(); i++){
            itServiceDTOS.add(makeAnITServiceDTO(new ITServiceDTO(), itServices.get(i)));
        }
        return itServiceDTOS;
    }

    public ITServiceDTO makeAnITServiceDTO(ITServiceDTO itServiceDTO, ITService itService){
        itServiceDTO.setId(itService.getId());
        itServiceDTO.setPrice(itService.getPrice());
        itServiceDTO.setTitle(itService.getTitle());
        itServiceDTO.setDescription(itService.getDescription());

        return itServiceDTO;
    }

    public ITService makeAnITService(ITServiceDTO itServiceDTO, ITService itService) {
        itService.setPrice(itServiceDTO.getPrice());
        itService.setTitle(itServiceDTO.getTitle());
        itService.setDescription(itServiceDTO.getDescription());

        return itService;
    }

    public ITServiceDTO saveITService(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        itServiceRepository.save(makeAnITService(itServiceDTO, itService));
        return itServiceDTO;
    }

    public void deleteITService(Long id) {
        itServiceRepository.deleteById(id);
    }

    public ITServiceDTO editITService(ITServiceDTO itServiceDTO) {
        ITService itService = new ITService();
        itService.setId(itServiceDTO.getId());
        itServiceRepository.save(makeAnITService(itServiceDTO, itService));
        return itServiceDTO;
    }

}
