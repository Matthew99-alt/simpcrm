package com.crm.filestorage.uploadClasses;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileRequest {

    private MultipartFile file;

    private Long orderId;

    private ObjectId id;

}
