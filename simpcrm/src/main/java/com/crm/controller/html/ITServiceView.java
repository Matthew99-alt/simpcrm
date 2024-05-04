package com.crm.controller.html;

import com.crm.dto.ITServiceDTO;
import com.crm.dto.UserDTO;
import com.crm.service.ITServiceService;
import com.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/itservice")
@RequiredArgsConstructor
public class ITServiceView {

    private final ITServiceService itServiceService;

    @GetMapping("/all")
    public String getAllITServices(Model model) {
        List<ITServiceDTO> allITServices = itServiceService.findAllServices();
        model.addAttribute("itservices", allITServices);
        return "itservices/itservices.html";
    }

    @GetMapping("/personalPage/{userId}")
    public String getITService(@PathVariable("userId") Long userId, Model model) {
        ITServiceDTO userDTO = itServiceService.findById(userId);
        model.addAttribute("user", userDTO);
        return "user/personal_page.html";
    }

}
