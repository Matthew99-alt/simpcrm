package com.crm.controller;

import com.crm.annotation.LoggingMethod;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/rest/fileStorage")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;


    @LoggingMethod(role = "admin")
    @GetMapping("/fileByOrderId")
    FileStorage getFileByOrderId(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam(name = "orderId") Long orderId
    ) {
        return fileStorageService.getFileByOrderId(orderId);
    }

    @LoggingMethod(role = {"admin", "user"})
    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password
    ) {
        return fileStorageService.getAllFilesFromStorage();
    }


    @LoggingMethod(role = "admin")
    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @PathVariable String fileId
    ) throws IOException {
        return fileStorageService.downloadFile(fileId);
    }


    @LoggingMethod(role = {"admin", "user"})
    @DeleteMapping("/delete")
    public void deleteFile(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam("id") String id
    ) {
        fileStorageService.deleteFileById(id);
    }

    @LoggingMethod(role = {"admin", "user"})
    @DeleteMapping("/deleteByOrderId")
    public void deleteFile(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @RequestParam("orderId") Long orderId
    ) {
        fileStorageService.deleteFileByOrderId(orderId);
    }

    @LoggingMethod(role = "admin")
    @PutMapping("/edit")
    public void editFile(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @ModelAttribute UploadClass uploadClass
    ) throws IOException {
        fileStorageService.editFile(uploadClass);
    }

    @LoggingMethod(role = "admin")
    @PostMapping("/upload")
    String uploadFile(
            @RequestHeader(value = "login", required = false) String login,
            @RequestHeader(value = "password", required = false) String password,
            @ModelAttribute UploadClass uploadClass
    ) throws IOException {
        return fileStorageService.addFile(uploadClass);
    }
}
