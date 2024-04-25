package com.crm.client;

import com.crm.dto.FileStorage;
import com.crm.dto.HealthCheckFileStorageResponse;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-storage-client", url = "${service.clients.file-storage-client.url}")
public interface FileStorageClient {

    @GetMapping(value = "/main/health", consumes = MediaType.APPLICATION_JSON_VALUE)
    HealthCheckFileStorageResponse healthCheckFileStorageApp();

    @GetMapping("/rest/fileStorage/all")
    List<FileStorage> getAllFiles();

    @GetMapping("/rest/fileStorage/files/{fileId}")
    ResponseEntity<byte[]> downloadFile(@PathVariable ObjectId fileId) throws IOException;

    @GetMapping(value = "/rest/fileStorage/fileTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getFileTitle(@RequestBody FileStorage fileStorage);

    @DeleteMapping("/rest/fileStorage/delete")
    void deleteFile(@RequestBody FileStorage FileStorage);

    @PutMapping(value = "/rest/fileStorage/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void editFile(@RequestPart MultipartFile file,
                  @RequestParam("description") String description,
                  @RequestParam("id") ObjectId id);

    @PostMapping(value = "/rest/fileStorage/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart MultipartFile file,
                      @RequestParam("description") String description) throws IOException;
}