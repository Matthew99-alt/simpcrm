package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.dto.FileStorage;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient fileStorageClient;

    public List<FileStorage> getAllFilesFromStorage() {
        return fileStorageClient.getAllFiles();
    }

    public void deleteFile(FileStorage fileStorage) {
        fileStorageClient.deleteFile(fileStorage);
    }

    public void editFile(MultipartFile file, String description, String id, Long orderId) {
        fileStorageClient.editFile(file, description, id, orderId);
    }

    public ResponseEntity<byte[]> downloadFile(String id) throws IOException {
        return fileStorageClient.downloadFile(id);
    }

    public String addFile(MultipartFile file, String description, Long orderId) throws IOException {
        return fileStorageClient.uploadFile(file, description, orderId);
    }

    public List<FileStorage> getFileById(Long orderId) {
        return fileStorageClient.getFileByOrderId(orderId);
    }
}
