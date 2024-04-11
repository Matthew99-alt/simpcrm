package com.crm.filestorage.controller;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.service.FileStorageService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/save")
    public FileStorage saveUser(@RequestBody FileStorageDTO fileStorageDTO) {
        return fileStorageService.saveUser(fileStorageDTO);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody FileStorageDTO fileStorageDTO) {
        fileStorageService.deleteUser(fileStorageDTO);
    }

    @PutMapping("/edit")
    public void editUser(@RequestBody FileStorageDTO fileStorageDTO) {
        fileStorageService.editUser(fileStorageDTO);
    }
}
