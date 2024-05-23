package com.crm.controller.rest;

import com.crm.dto.StatusDTO;
import com.crm.service.StatusService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/all")
    public List<StatusDTO> getAllStatuses() {
        return statusService.findAllStatuses();
    }

    @PostMapping("/save")
    public StatusDTO saveStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.saveStatus(statusDTO);
    }

    @DeleteMapping("/delete")
    public void deleteStatus(@RequestParam Long id) {
        statusService.deleteStatus(id);
    }

    @PutMapping("/edit")
    public StatusDTO editStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.editStatus(statusDTO);
    }
}
