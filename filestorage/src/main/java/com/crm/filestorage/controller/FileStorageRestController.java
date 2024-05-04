package com.crm.filestorage.controller;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.service.FileStorageService;
import java.io.IOException;
import java.util.List;

import com.crm.filestorage.uploadClasses.UploadClass;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<FileStorageDTO> getFileByOrderId(@RequestParam(name = "orderId") Long orderId) {
        return fileStorageService.findByIdOrderId(orderId);
    }

    @PostMapping("/upload")
    //TODO: сделать класс-тело запроса, поместить в него ровно те же поля
    // @RequestBody - class uploadFileRequest
    public String uploadFile(@ModelAttribute UploadClass uploadClass) throws IOException {
        return fileStorageService.addFile(uploadClass);
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable ObjectId fileId) throws IOException {
        return fileStorageService.downloadFile(fileId);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("id") ObjectId id) {
        fileStorageService.deleteFile(id);
    }

    @PutMapping("/edit")
    public void editFile(@ModelAttribute UploadClass uploadClass)
            throws IOException {
        fileStorageService.editFile(uploadClass.getFile(), uploadClass.getDescription(), uploadClass.getId(), uploadClass.getOrderId());
    }
}
