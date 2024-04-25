package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.controller.FileStorageController.HealthCheckResponse;
import com.crm.dto.FileStorage;
import com.crm.dto.HealthCheckFileStorageResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient fileStorageClient;

    public HealthCheckResponse checkMongoApp() {
        HealthCheckFileStorageResponse healthCheckFileStorageResponse = fileStorageClient.healthCheckFileStorageApp();
        return new HealthCheckResponse(healthCheckFileStorageResponse.getHealthy(), "Это проверка сервиса file storage");
    }

    public List<FileStorage> getAllFilesFromStorage() {
        return fileStorageClient.getAllFiles();
    }

    public void deleteFile(FileStorage fileStorage) {
        fileStorageClient.deleteFile(fileStorage);
    }

    public void editFile(MultipartFile file, String description, String id) {
        fileStorageClient.editFile(file, description, id);
    }

    public ResponseEntity<byte[]> downloadFile(String id) throws IOException {
        return fileStorageClient.downloadFile(id);
    }

    public String addFile(MultipartFile file, String description) throws IOException {
        return fileStorageClient.uploadFile(file, description);
    }

    public String getFileTitle(FileStorage fileStorage) {
        return fileStorageClient.getFileTitle(fileStorage);
    }
}
