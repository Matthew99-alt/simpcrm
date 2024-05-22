package com.crm.filestorage.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileStorageDTO {

    private String id;

    private Long size;

    private String title;

    private String description;

    private byte[] file;

    private Long orderId;

}
