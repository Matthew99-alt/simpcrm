package com.crm.controller.rest;

import com.crm.dto.StatusDTO;
import com.crm.entity.Status;
import com.crm.service.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/title")
    public StatusDTO getByStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.findByStatus(statusDTO.getStatus());
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
    public StatusDTO editStatus(@RequestBody StatusDTO statusDTO){ return statusService.editStatus(statusDTO);}
}
