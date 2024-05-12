package com.crm.uploadClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadClass {

    private MultipartFile file;

    private String description;

    private String id;

    private Long orderId;

}
