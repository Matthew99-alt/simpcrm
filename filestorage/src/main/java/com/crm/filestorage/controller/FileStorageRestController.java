package com.crm.filestorage.controller;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.service.FileStorageService;

import java.io.IOException;
import java.util.List;

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
    public List<FileStorage> getAllUsers() {
        return fileStorageService.findAll();
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws IOException {
        return fileStorageService.addFile(file, description);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody FileStorageDTO fileStorageDTO) {
        fileStorageService.deleteFile(fileStorageDTO);
    }

    @PutMapping("/edit")
    public void editUser(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws IOException {
        fileStorageService.editFile(file,description);
    }

}
