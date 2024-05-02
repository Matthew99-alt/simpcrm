package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.dto.FileStorage;
import java.io.IOException;
import java.util.List;

import com.crm.uploadClass.UploadClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient fileStorageClient;

    public List<FileStorage> getAllFilesFromStorage() {
        return fileStorageClient.getAllFiles();
    }

    public void deleteFile(String id) {
        fileStorageClient.deleteFile(id);
    }

    public void editFile(UploadClass uploadClass) throws IOException {
        fileStorageClient.editFile(uploadClass);
    }

    public ResponseEntity<byte[]> downloadFile(String id) throws IOException {
        return fileStorageClient.downloadFile(id);
    }

    public String addFile(UploadClass uploadClass) throws IOException {
        return fileStorageClient.uploadFile(uploadClass);
    }

    public List<FileStorage> getFileById(Long orderId) {
        return fileStorageClient.getFileByOrderId(orderId);
    }
}
