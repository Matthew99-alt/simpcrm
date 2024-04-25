package com.crm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileStorage {

    //TODO: а нужен ли ObjectId здесь?? посмотри то тебе отвечает FeignClient в отладке
    private String id;

    private Long size;

    private String title;

    private String description;

    private byte[] file;
}
