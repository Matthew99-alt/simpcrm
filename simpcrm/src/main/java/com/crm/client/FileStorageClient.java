package com.crm.client;

import com.crm.dto.FileStorage;
import com.crm.dto.HealthCheckFileStorageResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "file-storage-client", url = "${service.clients.file-storage-client.url}")
public interface FileStorageClient {

    @GetMapping(value = "/main/health", consumes = MediaType.APPLICATION_JSON_VALUE)
    HealthCheckFileStorageResponse healthCheckFileStorageApp();

    @GetMapping("/rest/fileStorage/all")
    List<FileStorage> getAllFiles();
}