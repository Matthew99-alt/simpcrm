package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.controller.MainController.HealthCheckResponse;
import com.crm.dto.FileStorage;
import com.crm.dto.HealthCheckFileStorageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthCheckerService {

    private final FileStorageClient fileStorageClient;

    public HealthCheckResponse checkMongoApp() {
        HealthCheckFileStorageResponse healthCheckFileStorageResponse = fileStorageClient.healthCheckFileStorageApp();
        return new HealthCheckResponse(healthCheckFileStorageResponse.getHealthy(), "Это проверка сервиса file storage");
    }

    public List<FileStorage> getAllFilesFromStorage() {
        return fileStorageClient.getAllFiles();
    }
}
