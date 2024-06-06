package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.StatusDTO;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.SecurityService;
import com.crm.service.StatusService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<StatusDTO> getAllStatuses(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password) {
            return statusService.findAllStatuses();
    }

    @LoggingMethod(role = {"admin"})
    @PostMapping("/save")
    public StatusDTO saveStatus(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestBody StatusDTO statusDTO) {
            return statusService.saveStatus(statusDTO);
    }
    @LoggingMethod(role = "admin")
    @DeleteMapping("/delete")
    public void deleteStatus(@RequestHeader("login") String login,
                             @RequestHeader("password") String password,
                             @RequestParam Long id) {
            statusService.deleteStatus(id);
    }

    @LoggingMethod(role = {"admin"})
    @PutMapping("/edit")
    public StatusDTO editStatus(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestBody StatusDTO statusDTO) {
            return statusService.editStatus(statusDTO);
    }
}
