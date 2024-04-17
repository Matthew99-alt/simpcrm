package com.crm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileStorage {

    private String id;

    private Long size;

    private String title;

    private String description;

    private byte[] file;
}
