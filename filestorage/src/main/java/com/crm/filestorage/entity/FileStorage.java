package com.crm.filestorage.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Document(collection = "filesTable")
public class FileStorage {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @Field(name = "fileLink")
    private String fileLink;
    @Field(name = "fileTitle")
    private String fileTitle;

    //TODO: поиграйся с именем поля
    @Field(name = "documentsByte")
    private byte[] documentsByte;

    /*
    public byte[] getDocumentsByte() {
        return documentsByte;
    }

    public void setDocumentsByte(byte[] documentsByte) {
        this.documentsByte = documentsByte;
    }

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

     */
}
