
package com.crm.service;

import com.crm.dto.AmenitiesDTO;
import com.crm.entity.Amenities;
import com.crm.reposotiry.AmenitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenitiesService {
    private final AmenitiesRepository amenitiesRepository;

    public List<AmenitiesDTO> findAllAmenities() {
        List<Amenities> amenities = amenitiesRepository.findAll();
        ArrayList<AmenitiesDTO> amenitiesDTOS = new ArrayList<>();
        for (Amenities amenity : amenities) {
            amenitiesDTOS.add(makeAmenitiesDTO(new AmenitiesDTO(), amenity));
        }
        return amenitiesDTOS;
    }

    public AmenitiesDTO makeAmenitiesDTO(AmenitiesDTO amenitiesDTO, Amenities amenities){
        amenitiesDTO.setId(amenities.getId());
        amenitiesDTO.setPrice(amenities.getPrice());
        amenitiesDTO.setTitle(amenities.getTitle());
        amenitiesDTO.setDescription(amenities.getDescription());
        amenitiesDTO.setRatio(amenities.getRatio());

        return amenitiesDTO;
    }

    public Amenities makeAnAmenities(AmenitiesDTO amenitiesDTO, Amenities amenities) {
        amenities.setPrice(amenitiesDTO.getPrice());
        amenities.setTitle(amenitiesDTO.getTitle());
        amenities.setDescription(amenitiesDTO.getDescription());
        amenities.setRatio(amenitiesDTO.getRatio());

        return amenities;
    }

    public AmenitiesDTO saveAmenities(AmenitiesDTO amenitiesDTO) {
        Amenities amenities = new Amenities();
        amenitiesRepository.save(makeAnAmenities(amenitiesDTO, amenities));
        amenitiesDTO.setId(amenities.getId());
        return amenitiesDTO;
    }

    public void deleteAmenities(Long id) {
        amenitiesRepository.deleteById(id);
    }

    public AmenitiesDTO editITService(AmenitiesDTO amenitiesDTO) {
        Amenities amenities = new Amenities();
        amenities.setId(amenitiesDTO.getId());
        amenitiesRepository.save(makeAnAmenities(amenitiesDTO, amenities));
        return amenitiesDTO;
    }

}
