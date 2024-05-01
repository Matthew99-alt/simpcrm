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

    private String statusTitle; //TODO Статус это отдельный объект и название, как раз и есть его свойство

    private String description;

    private List<String> itServices;

    private List<String> programs;

    private String comments;

    private String clientFirstName; // TODO: отдельная сущность Client (или User с каким-то типом)

    private String clientSecondName; // свойство client

    private String clientMiddleName; // свойство client

    private String userFirstName; // TODO: отдельная сущность User

    private String userSecondName; // свойство User

    private String userMiddleName; // свойство User

}
