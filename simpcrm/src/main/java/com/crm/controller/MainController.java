package com.crm.controller;

import com.crm.dto.FileStorage;
import com.crm.service.HealthCheckerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final HealthCheckerService healthCheckerService;

    @GetMapping("/health")
    public HealthCheckResponse healthCheck() {
        return new HealthCheckResponse(true, "Это проверка сервиса simp crm");
    }


    @GetMapping("/healthCheckMongoApp")
    public HealthCheckResponse healthCheckMongoApp() {
        return healthCheckerService.checkMongoApp();
    }

    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp() {
        return healthCheckerService.getAllFilesFromStorage();
    }

    public record HealthCheckResponse(Boolean healthy, String description) {

    }
}
