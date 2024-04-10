package com.crm.filestorage.dto;

import org.bson.types.ObjectId;

public class FileStorageDTO {
    private ObjectId id;

    private String fileLink;

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
