
package com.crm.client;

import com.crm.dto.FileStorage;

import java.io.IOException;
import java.util.List;

import com.crm.uploadClass.UploadClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-storage-client", url = "${service.clients.file-storage-client.url}")
public interface FileStorageClient {

    @GetMapping("/rest/fileStorage/all")
    List<FileStorage> getAllFiles();

    @GetMapping("/rest/fileStorage/fileByOrderId")
    List<FileStorage> getFileByOrderId(Long orderId);

    @GetMapping("/rest/fileStorage/files/{fileId}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) throws IOException;


    @DeleteMapping("/rest/fileStorage/delete")
    void deleteFile(@RequestParam("id") String id);

    @PutMapping(value = "/rest/fileStorage/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void editFile(@ModelAttribute UploadClass uploadClass) throws IOException;

    @PostMapping(value = "/rest/fileStorage/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@ModelAttribute UploadClass uploadClass) throws IOException;
}