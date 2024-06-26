package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.dto.FileStorage;
import com.crm.uploadClass.UploadClass;
import java.io.IOException;
import java.util.List;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileStorageClient fileStorageClient;

    public List<FileStorage> getAllFilesFromStorage() {
        return fileStorageClient.getAllFiles();
    }

    public void deleteFileById(String id) {
            fileStorageClient.deleteFile(id);
    }

    public void deleteFileByOrderId(Long orderId) {
        fileStorageClient.deleteFileByOrderId(orderId);
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

    public FileStorage getFileByOrderId(Long orderId) {
        try {
            return fileStorageClient.getFileByOrderId(orderId);
        } catch (FeignException e) {
            if (e.status() == 500) {
                // Возвращаем пустое значение или null при ошибке 500
                return null;
            }
            throw e; // Пробрасываем другие ошибки
        }
    }

    public List<FileStorage> getFilesWithoutOrderId(){
        return fileStorageClient.getFilesWithoutOrderId();
    }
}
