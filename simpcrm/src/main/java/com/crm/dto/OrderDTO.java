package com.crm.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private String orderName;

    private Long priority;

    private String statusTitle;

    private String description;

    private List<String> itServices;

    private List<String> programs;

    private String comments;

    private String clientFirstName;

    private String clientSecondName;

    private String clientMiddleName;

    private String userFirstName;

    private String userSecondName;

    private String userMiddleName;


}
