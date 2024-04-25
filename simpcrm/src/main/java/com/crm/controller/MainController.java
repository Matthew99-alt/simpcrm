package com.crm.controller;

import com.crm.dto.FileStorage;
import com.crm.service.HealthCheckerService;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    @GetMapping("/fileTitle")
    String getFileTitle(@RequestBody FileStorage fileStorage){
        return healthCheckerService.getFileTitle(fileStorage);
    }

    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp() {
        return healthCheckerService.getAllFilesFromStorage();
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable ObjectId fileId) throws IOException {
        return healthCheckerService.downloadFile(fileId);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestBody FileStorage fileStorage) {
        healthCheckerService.deleteFile(fileStorage);
    }

    @PutMapping("/edit")
    public void editFile(@RequestParam("file") MultipartFile file,
                         @RequestParam("description") String description,
                         @RequestParam("id") ObjectId id) {
        healthCheckerService.editFile(file,description,id);
    }

    @PostMapping("/upload")
    String uploadFile(@RequestParam("file") MultipartFile file,
                      @RequestParam("description") String description) throws IOException{
        return healthCheckerService.addFile(file,description);
    };


    public record HealthCheckResponse(Boolean healthy, String description) {

    }
}
