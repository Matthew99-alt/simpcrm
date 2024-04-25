package com.crm.controller.turnedOff;

import com.crm.dto.StatusDTO;
import com.crm.entity.Status;
import com.crm.service.turnedOff.StatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/rest/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/all")
    public List<Status> getAllStatus() {
        return statusService.findAllUsers();
    }

    @GetMapping("/title")
    public List<Status> getByStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.findByStatus(statusDTO.getStatus());
    }

    @PostMapping("/save")
    public Status saveStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.saveStatus(statusDTO);
    }

    @DeleteMapping("/delete")
    public void deleteStatus(@RequestBody StatusDTO statusDTO) {
        statusService.deleteStatus(statusDTO);
    }

    @PutMapping("/edit")
    public Status editStatus(@RequestBody StatusDTO statusDTO){ return statusService.editStatus(statusDTO);}
}
