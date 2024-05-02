package com.crm.filestorage.service;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.crm.filestorage.uploadClasses.UploadClass;
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

    public List<FileStorageDTO> findByIdOrderId(long id) {
        return fileStorageRepository.findAllByOrderId(id).stream()
                .map(this::convertToDto)
                .toList();
    }

    private FileStorageDTO convertToDto(FileStorage fileSTorage) {
        FileStorageDTO fileStorageDTO = new FileStorageDTO();
        fileStorageDTO.setId(fileSTorage.getId());
        fileStorageDTO.setDescription(fileSTorage.getDescription());
        fileStorageDTO.setFile(fileSTorage.getFile());
        fileStorageDTO.setSize(fileSTorage.getSize());
        fileStorageDTO.setTitle(fileSTorage.getTitle());
        fileStorageDTO.setOrderId(fileSTorage.getOrderId());

        return fileStorageDTO;
    }

    public void deleteFile(ObjectId id) {
        FileStorage fileStorage = fileStorageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        fileStorageRepository.delete(fileStorage);
    }

    public void editFile(MultipartFile file,
                         String description,
                         ObjectId id, Long orderId) throws IOException {
        FileStorage files = FileStorage
                .builder()
                .id(id)
                .title(file.getOriginalFilename())
                .description(description)
                .size(file.getSize())
                .file(file.getBytes())
                .orderId(orderId)
                .build();
        fileStorageRepository.save(files);
    }

    public String addFile(UploadClass uploadClass) throws IOException {
        FileStorage files = FileStorage
                .builder()
                .title(uploadClass.getFile().getOriginalFilename())
                .size(uploadClass.getFile().getSize())
                .description(uploadClass.getDescription())
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