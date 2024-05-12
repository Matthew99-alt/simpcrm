package com.crm.controller.rest;

import com.crm.dto.FileStorage;
import com.crm.service.FileStorageService;
import java.io.IOException;
import java.util.List;

import com.crm.uploadClass.UploadClass;
import lombok.RequiredArgsConstructor;
//import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileStorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/fileByOrderId")
    FileStorage getFileTitle(@RequestParam Long orderId) {
        return fileStorageService.getFileById(orderId);
    }

    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp() {
        return fileStorageService.getAllFilesFromStorage();
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) throws IOException {
        return fileStorageService.downloadFile(fileId);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("id") String id) {
        fileStorageService.deleteFile(id);
    }

    @PutMapping("/edit")
    public void editFile(@ModelAttribute UploadClass uploadClass) throws IOException {
        fileStorageService.editFile(uploadClass);
    }

    @PostMapping("/upload")
    String uploadFile(@ModelAttribute UploadClass uploadClass) throws IOException {
        return fileStorageService.addFile(uploadClass);
    }
}
