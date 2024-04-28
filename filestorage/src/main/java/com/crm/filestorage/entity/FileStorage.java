package com.crm.filestorage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Getter
@Setter
@ToString
@Document(collection = "filesTable")
public class FileStorage {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(name = "size")
    private Long size;

    @Field(name = "title")
    private String title;

    @Field(name = "description")
    private String description;

    @Field(name = "file")
    private byte[] file;

    @Field(name = "order_id")
    private Long orderId;

}
