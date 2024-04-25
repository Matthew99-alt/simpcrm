package com.crm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class FileStorage {

    private ObjectId id;

    private Long size;

    private String title;

    private String description;

    private byte[] file;
}
