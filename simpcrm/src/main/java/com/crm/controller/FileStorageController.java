package com.crm.controller;

import com.crm.dto.FileStorage;
import com.crm.service.FileStorageService;
import com.crm.uploadClass.UploadClass;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fileStorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/fileByOrderId")
    FileStorage getFileByOrderId(@RequestParam(name = "orderId") Long orderId) {
        return fileStorageService.getFileByOrderId(orderId);
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
        fileStorageService.deleteFileById(id);
    }

    @DeleteMapping("/deleteByOrderId")
    public void deleteFile(@RequestParam("orderId") Long orderId) {
        fileStorageService.deleteFileByOrderId(orderId);
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
