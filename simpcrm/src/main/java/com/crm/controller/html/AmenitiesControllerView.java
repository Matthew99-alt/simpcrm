package com.crm.controller.html;

import com.crm.dto.MerchandiseDTO;
import com.crm.service.MerchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/program")
@RequiredArgsConstructor
public class AmenitiesControllerView {

    private final MerchandiseService merchandiseService;


    @GetMapping("/all")
    public String getAllPrograms(Model model) {
        List<MerchandiseDTO> allPrograms = merchandiseService.findAllPrograms();
        model.addAttribute("programs", allPrograms);
        return "program/program.html";
    }

}
