package com.crm.uploadClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadClass {

    byte[] file;

    String description;

    String id;

    Long orderId;

}
