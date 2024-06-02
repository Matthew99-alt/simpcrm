package com.crm.controller;

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

    private final SecurityService securityService;

    @GetMapping("/fileByOrderId")
    FileStorage getFileByOrderId(@RequestHeader("login") String login,
                                 @RequestHeader("password") String password,
                                 @RequestParam(name = "orderId") Long orderId) {
        return fileStorageService.getFileByOrderId(orderId);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return fileStorageService.getFileByOrderId(orderId);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }
    }

    @GetMapping("/getAllFiles")
    public List<FileStorage> getAllFilesFromMongoApp(@RequestHeader("login") String login,
                                                     @RequestHeader("password") String password) {
        return fileStorageService.getAllFilesFromStorage();
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return fileStorageService.getAllFilesFromStorage();
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("login") String login,
                                               @RequestHeader("password") String password,
                                               @PathVariable String fileId) throws IOException {

        return fileStorageService.downloadFile(fileId);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return fileStorageService.downloadFile(fileId);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestHeader("login") String login,
                           @RequestHeader("password") String password,
                           @RequestParam("id") String id) {
        fileStorageService.deleteFileById(id);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            fileStorageService.deleteFileById(id);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @DeleteMapping("/deleteByOrderId")
    public void deleteFile(@RequestHeader("login") String login,
                           @RequestHeader("password") String password,
                           @RequestParam("orderId") Long orderId) {
        fileStorageService.deleteFileByOrderId(orderId);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            fileStorageService.deleteFileByOrderId(orderId);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @PutMapping("/edit")
    public void editFile(@RequestHeader("login") String login,
                         @RequestHeader("password") String password,
                         @ModelAttribute UploadClass uploadClass) throws IOException {
        fileStorageService.editFile(uploadClass);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            fileStorageService.editFile(uploadClass);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }

    }

    @PostMapping("/upload")
    String uploadFile(@RequestHeader("login") String login,
                      @RequestHeader("password") String password,
                      @ModelAttribute UploadClass uploadClass) throws IOException {
        return fileStorageService.addFile(uploadClass);
//        if (securityService.checkAdminRole(login, password, loggingMethod.role())) {
//            return fileStorageService.addFile(uploadClass);
//        } else {
//            throw new PermissionDeniedException("В доступе отказано");
//        }
    }
}
