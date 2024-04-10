package com.crm.filestorage.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="filesTable")
public class FileStorage {

    @Id
    private ObjectId id;
    @Field(name="fileLink")
    private String fileLink;
    @Field(name="fileTitle")
    private String fileTitle;

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFileLink() {
        return fileLink;
    }

    public String getFileTitle() {
        return fileTitle;
    }
}
