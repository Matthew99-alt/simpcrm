
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

    @GetMapping("/all")
    List<FileStorage> getAllFiles();

    @GetMapping("/fileByOrderId")
    FileStorage getFileByOrderId(@RequestParam(name = "orderId") Long orderId);

    @GetMapping("/{fileId}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) throws IOException;

    @DeleteMapping("/delete")
    void deleteFile(@RequestParam("id") String id);

    @DeleteMapping("/deleteByOrderId")
    void deleteFileByOrderId(@RequestParam("orderId") Long orderId);

    @PutMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void editFile(@ModelAttribute UploadClass uploadClass) throws IOException;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@ModelAttribute UploadClass uploadClass) throws IOException;
}