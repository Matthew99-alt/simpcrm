package com.crm.filestorage.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/health")
    public HealthCheck healthCheck() {
        return new HealthCheck(true);
    }


    @Getter
    public static class HealthCheck {
        private final Boolean healthy;

        HealthCheck(Boolean healthy) {
            this.healthy = healthy;
        }

    }
}
