package com.crm.controller.html;

import com.crm.dto.AmenitiesDTO;
import com.crm.service.AmenitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/itservice")
@RequiredArgsConstructor
public class MerchandiseView {

    private final AmenitiesService amenitiesService;

    @GetMapping("/all")
    public String getAllITServices(Model model) {
        List<AmenitiesDTO> allITServices = amenitiesService.findAllAmenities();
        model.addAttribute("itservices", allITServices);
        return "itservices/itservices.html";
    }

}
