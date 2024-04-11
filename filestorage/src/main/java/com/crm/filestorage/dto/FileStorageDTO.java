package com.crm.filestorage.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class FileStorageDTO {

    //TODO: я же создаю новую сущность?
    // подумать, не замечание
    private ObjectId id;

    private String fileLink;

    private String fileTitle;

    //TODO: добавить byte[]
    // как передавать файлы в POST запросе (mongo не при чем!!!!)
    // как хранить byte[] в mongo (тут она при чем!!!)

    /*
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
