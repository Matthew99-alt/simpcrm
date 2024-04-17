package com.crm.filestorage.service;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
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

    public void deleteFile(FileStorageDTO fileStorageDTO) {
        List<FileStorage> fileStorage = fileStorageRepository.findByTitle(fileStorageDTO.getTitle());
        fileStorageRepository.deleteAll(fileStorage);
    }

    public void editFile( MultipartFile file,String description) throws IOException {
        FileStorage fileStorage = fileStorageRepository.findOneByTitle(file.getName());
        FileStorage files = FileStorage
                .builder()
                .id(fileStorage.getId())
                .title(file.getName())
                .description(description)
                .file(file.getBytes())
                .build();
        fileStorageRepository.save(files);
    }

    public String addFile(MultipartFile file, String description) throws IOException {
        FileStorage files = FileStorage
                .builder()
                .title(file.getName())
                .size(file.getSize())
                .description(description)
                .file(file.getBytes())
                .build();
        fileStorageRepository.save(files);
        if(files.getId() != null){
            return "File uploaded successfully into database";
        }
        return null;
    }

}
