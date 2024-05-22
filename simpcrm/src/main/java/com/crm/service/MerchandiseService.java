
package com.crm.service;

import com.crm.dto.MerchandiseDTO;
import com.crm.entity.Merchandise;
import com.crm.reposotiry.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public List<MerchandiseDTO> findAllPrograms() {
        List<Merchandise> merchandises = merchandiseRepository.findAll();
        ArrayList<MerchandiseDTO> merchandiseDTOS = new ArrayList<>();
        for (Merchandise merchandise : merchandises) {
            merchandiseDTOS.add(makeAMerchandiseDTO(new MerchandiseDTO(), merchandise));
        }
        return merchandiseDTOS;
    }


    public MerchandiseDTO makeAMerchandiseDTO(MerchandiseDTO merchandiseDTO, Merchandise merchandise) {
        merchandiseDTO.setId(merchandise.getId());
        merchandiseDTO.setTitle(merchandise.getTitle());
        merchandiseDTO.setDescription(merchandise.getDescription());
        merchandiseDTO.setPrice(merchandise.getPrice());
        merchandiseDTO.setRatio(merchandise.getRatio());
        merchandiseDTO.setNumberInWarehouse(merchandise.getNumberInWarehouse());

        return merchandiseDTO;
    }

    public Merchandise makeAMerchandise(MerchandiseDTO merchandiseDTO, Merchandise merchandise) {
        merchandise.setTitle(merchandiseDTO.getTitle());
        merchandise.setDescription(merchandiseDTO.getDescription());
        merchandise.setPrice(merchandiseDTO.getPrice());
        merchandise.setRatio(merchandiseDTO.getRatio());
        merchandise.setNumberInWarehouse(merchandiseDTO.getNumberInWarehouse());

        return merchandise;
    }

    public MerchandiseDTO saveMerchandise(MerchandiseDTO merchandiseDTO) {
        Merchandise merchandise = new Merchandise();
        merchandiseRepository.save(makeAMerchandise(merchandiseDTO, merchandise));
        merchandiseDTO.setId(merchandise.getId());
        return merchandiseDTO;
    }

    public void deleteMerchandise(Long id) {
        merchandiseRepository.deleteById(id);
    }

    public MerchandiseDTO editMerchandise(MerchandiseDTO merchandiseDTO) {
        Merchandise merchandise = new Merchandise();
        merchandise.setId(merchandiseDTO.getId());
        merchandiseRepository.save(makeAMerchandise(merchandiseDTO, merchandise));
        return merchandiseDTO;
    }
}
