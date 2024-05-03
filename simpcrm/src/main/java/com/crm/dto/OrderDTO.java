package com.crm.dto;

import java.io.File;
import java.util.List;

import com.crm.entity.ITService;
import com.crm.entity.Program;
import com.crm.entity.Status;
import com.crm.entity.User;
import com.crm.uploadClass.UploadClass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private String orderName;

    private Long priority;

    private Status status;

    private String description;

    private List<ITServiceDTO> itServices;

    private List<ProgramDTO> programs;

    private String comments;

    private User client;

    private User users;

    private List<FileStorage> fileStorages;
}
