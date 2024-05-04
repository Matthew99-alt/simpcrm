package com.crm.controller.html;

import com.crm.dto.StatusDTO;
import com.crm.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusControllerView {

    private final StatusService statusService;

    @GetMapping("/all")
    public String getAllStatuses(Model model) {
        List<StatusDTO> allStatuses = statusService.findAllStatuses();
        model.addAttribute("statuses", allStatuses);
        return "status/status.html";
    }

    @GetMapping("/personalPage/{statusId}")
    public String getStatus(@PathVariable("statusId") Long statusId, Model model) {
        StatusDTO statusDTO = statusService.findById(statusId);
        model.addAttribute("statuses", statusDTO);
        return "status/status_page.html";
    }
}
