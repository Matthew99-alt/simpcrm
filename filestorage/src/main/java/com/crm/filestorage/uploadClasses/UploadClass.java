package com.crm.filestorage.uploadClasses;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadClass {
    MultipartFile file;

    String description;

    Long orderId;

    ObjectId id;
}
