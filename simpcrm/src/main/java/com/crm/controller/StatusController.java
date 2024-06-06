package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.StatusDTO;
import com.crm.service.StatusService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/rest/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/all")
    public List<StatusDTO> getAllStatuses(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login
    ) {
        return statusService.findAllStatuses();
    }

    @LoggingMethod(role = {"admin"})
    @PostMapping("/save")
    public StatusDTO saveStatus(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestBody StatusDTO statusDTO
    ) {
        return statusService.saveStatus(statusDTO);
    }

    @LoggingMethod(role = "admin")
    @DeleteMapping("/delete")
    public void deleteStatus(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestParam Long id
    ) {
        statusService.deleteStatus(id);
    }

    @LoggingMethod(role = {"admin"})
    @PutMapping("/edit")
    public StatusDTO editStatus(
            @RequestHeader(value = "password", required = false) String password,
            @RequestHeader(value = "login", required = false) String login,
            @RequestBody StatusDTO statusDTO
    ) {
        return statusService.editStatus(statusDTO);
    }
}
