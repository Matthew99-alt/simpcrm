package com.crm.filestorage.controller;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.service.FileStorageService;
import com.crm.filestorage.uploadClasses.UploadFileRequest;
import java.io.IOException;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
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
@RequestMapping("/rest/fileStorage")
public class FileStorageRestController {

    private final FileStorageService fileStorageService;

    public FileStorageRestController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/all")
    public List<FileStorage> getAllFiles() {
        return fileStorageService.findAll();
    }

    @GetMapping("/fileByOrderId")
    public FileStorageDTO getFileByOrderId(@RequestParam(name = "orderId") Long orderId) {
        return fileStorageService.findByIdOrderId(orderId);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable ObjectId fileId) {
        return fileStorageService.downloadFile(fileId);
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute UploadFileRequest uploadFileRequest) throws IOException {
        return fileStorageService.addFile(uploadFileRequest);
    }

    @PutMapping("/edit")
    public void editFile(@ModelAttribute UploadFileRequest uploadFileRequest) throws IOException {
        fileStorageService.editFile(uploadFileRequest.getFile(), uploadFileRequest.getDescription(), uploadFileRequest.getId(), uploadFileRequest.getOrderId());
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("id") ObjectId id) {
        fileStorageService.deleteFile(id);
    }
}
