package com.crm.filestorage.service;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.crm.filestorage.uploadClasses.UploadFileRequest;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    public List<FileStorage> findAll() {
        return fileStorageRepository.findAll();
    }

    public FileStorageDTO findByIdOrderId(Long id) {
        FileStorage fileStorage = fileStorageRepository.findByOrderId(id);
        return convertToDto(fileStorage);
    }

    private FileStorageDTO convertToDto(FileStorage fileSTorage) {
        FileStorageDTO fileStorageDTO = new FileStorageDTO();

        fileStorageDTO.setId(fileSTorage.getId().toString());
        fileStorageDTO.setFile(fileSTorage.getFile());
        fileStorageDTO.setSize(fileSTorage.getSize());
        fileStorageDTO.setTitle(fileSTorage.getTitle());
        fileStorageDTO.setOrderId(fileSTorage.getOrderId());

        return fileStorageDTO;
    }

    public void deleteFile(ObjectId id) {
        fileStorageRepository.deleteById(id);
    }

    public void deleteFileByOrderId(Long orderId) {
        fileStorageRepository.deleteByOrderId(orderId);
    }

    public void editFile(MultipartFile file,
                         ObjectId id, Long orderId) throws IOException {
        FileStorage files = FileStorage
                .builder()
                .id(id)
                .title(file.getOriginalFilename())
                .size(file.getSize())
                .file(file.getBytes())
                .orderId(orderId)
                .build();
        fileStorageRepository.save(files);
    }

    public String addFile(UploadFileRequest uploadClass) throws IOException {
        FileStorage files = FileStorage
                .builder()
                .title(uploadClass.getFile().getOriginalFilename())
                .size(uploadClass.getFile().getSize())
                .orderId(uploadClass.getOrderId())
                .file(uploadClass.getFile().getBytes())
                .build();
        fileStorageRepository.save(files);
        if (files.getId() != null) {
            return "File uploaded successfully into database";
        }
        return null;
    }

    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable ObjectId fileId) {
        Optional<FileStorage> optionalFileDocument = fileStorageRepository.findById(fileId);

        if (optionalFileDocument.isPresent()) {
            FileStorage fileStorage = optionalFileDocument.get();
            byte[] fileBytes = fileStorage.getFile();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileStorage.getTitle());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileBytes));
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileBytes.length)
                    .body(resource);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}