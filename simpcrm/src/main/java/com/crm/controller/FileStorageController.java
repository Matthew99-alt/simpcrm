package com.crm.controller;

import com.crm.annotation.LoggingMethod;
import com.crm.dto.FileStorage;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.FileStorageService;
import com.crm.service.SecurityService;
import com.crm.uploadClass.UploadClass;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/fileStorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @GetMapping("/fileByOrderId")
    FileStorage getFileByOrderId(@RequestHeader("login") String login,
                                 @RequestHeader("password") String password,
                                 @RequestParam(name = "orderId") Long orderId) {
        return fileStorageService.getFileByOrderId(orderId);
    }
    @LoggingMethod(role = "admin")
    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp(@RequestHeader("login") String login,
                                                     @RequestHeader("password") String password) {
        return fileStorageService.getAllFilesFromStorage();
    }


    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("login") String login,
                                               @RequestHeader("password") String password,
                                               @PathVariable String fileId) throws IOException {

        return fileStorageService.downloadFile(fileId);
    }


    @DeleteMapping("/delete")
    public void deleteFile(@RequestHeader("login") String login,
                           @RequestHeader("password") String password,
                           @RequestParam("id") String id) {
        fileStorageService.deleteFileById(id);
    }

    @DeleteMapping("/deleteByOrderId")
    public void deleteFile(@RequestHeader("login") String login,
                           @RequestHeader("password") String password,
                           @RequestParam("orderId") Long orderId) {
        fileStorageService.deleteFileByOrderId(orderId);
    }

    @PutMapping("/edit")
    public void editFile(@RequestHeader("login") String login,
                         @RequestHeader("password") String password,
                         @ModelAttribute UploadClass uploadClass) throws IOException {
        fileStorageService.editFile(uploadClass);
    }

    @PostMapping("/upload")
    String uploadFile(@RequestHeader("login") String login,
                      @RequestHeader("password") String password,
                      @ModelAttribute UploadClass uploadClass) throws IOException {
        return fileStorageService.addFile(uploadClass);
    }
}
