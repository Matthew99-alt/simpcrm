package com.crm.controller.rest;

import com.crm.dto.FileStorage;
import com.crm.service.FileStorageService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
//import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileStorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/fileByOrderId")
    List<FileStorage> getFileTitle(@RequestParam Long orderId) {
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
    public void deleteFile(@RequestBody FileStorage fileStorage) {
        fileStorageService.deleteFile(fileStorage);
    }

    @PutMapping("/edit")
    public void editFile(@RequestParam("file") MultipartFile file,
                         @RequestParam("description") String description,
                         @RequestParam("id") String id,
                         @RequestParam("orderId") Long orderId) {
        fileStorageService.editFile(file, description, id, orderId);
    }

    @PostMapping("/upload")
    String uploadFile(@RequestParam("file") MultipartFile file,
                      @RequestParam("description") String description,
                      @RequestParam("orderId") Long orderId) throws IOException {
        return fileStorageService.addFile(file, description, orderId);
    }
}
