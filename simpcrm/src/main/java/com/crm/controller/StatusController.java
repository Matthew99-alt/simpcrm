package com.crm.controller;

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

    private final SecurityService securityService;

    @GetMapping("/all")
    public List<StatusDTO> getAllStatuses(@RequestHeader("login") String login,
                                          @RequestHeader("password") String password) {
        if (securityService.checkAdminRole(login, password)) {
            return statusService.findAllStatuses();
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PostMapping("/save")
    public StatusDTO saveStatus(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestBody StatusDTO statusDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return statusService.saveStatus(statusDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @DeleteMapping("/delete")
    public void deleteStatus(@RequestHeader("login") String login,
                             @RequestHeader("password") String password,
                             @RequestParam Long id) {
        if (securityService.checkAdminRole(login, password)) {
            statusService.deleteStatus(id);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }

    @PutMapping("/edit")
    public StatusDTO editStatus(@RequestHeader("login") String login,
                                @RequestHeader("password") String password,
                                @RequestBody StatusDTO statusDTO) {
        if (securityService.checkAdminRole(login, password)) {
            return statusService.editStatus(statusDTO);
        } else {
            throw new PermissionDeniedException("В доступе отказано");
        }
    }
}
