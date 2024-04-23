package com.crm.filestorage.controller;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.service.FileStorageService;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/upload")
    //TODO: сделать класс-тело запроса, поместить в него ровно те же поля
    // @RequestBody - class uploadFileRequest
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws IOException {
        return fileStorageService.addFile(file, description);
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable ObjectId fileId) throws IOException {
        return fileStorageService.downladFile(fileId);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody FileStorageDTO fileStorageDTO) {
        fileStorageService.deleteFile(fileStorageDTO);
    }

    @PutMapping("/edit")
    public void editFile(@RequestParam("file") MultipartFile file,
                         @RequestParam("description") String description,
                         @RequestParam("id") ObjectId id)
            throws IOException {
        fileStorageService.editFile(file, description, id);
    }

}
