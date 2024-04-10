package com.crm.filestorage.service;

import com.crm.filestorage.config.FileStorageConfig;
import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStorageService {
    public final FileStorageRepository fileStorageRepository;
    FileStorageConfig fileStorageConfig = new FileStorageConfig();
    MongoTemplate mongoTemplate = new MongoTemplate(fileStorageConfig.mongoClient(),"test");

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    public List<FileStorage> findAll() {
        return fileStorageRepository.findAll();
    }

    private FileStorage makeAFile(FileStorageDTO fileStorageDTO, boolean idEnable){
        FileStorage fileStorage = new FileStorage();
        if(idEnable){
            fileStorage.setId(fileStorageDTO.getId());
        }
        fileStorage.setFileLink(fileStorageDTO.getFileLink());
        fileStorage.setFileTitle(fileStorageDTO.getFileTitle());

        return fileStorage;
    }



    public FileStorage saveUser(FileStorageDTO fileStorageDTO) {
        return fileStorageRepository.save(makeAFile(fileStorageDTO, false));
    }
    public void deleteUser(FileStorageDTO fileStorageDTO) {
        List<FileStorage> fileStorage = fileStorageRepository.findByFileTitle(fileStorageDTO.getFileTitle());
        for(int i=0; i<fileStorage.size();i++) {
            mongoTemplate.remove(new Query(Criteria.where("_id")
                            .is(fileStorage.get(i).getId())
                            .where("fileTitle").is(fileStorage.get(i)
                            .getFileTitle()).where("fileLink")
                            .is(fileStorage.get(i).getFileLink())),
                            FileStorage.class);
        }
    }

    public void editUser(FileStorageDTO fileStorageDTO){
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(fileStorageDTO.getId())),Update.update("fileTitle",fileStorageDTO.getFileTitle()), FileStorage.class);
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(fileStorageDTO.getId())),Update.update("fileLink",fileStorageDTO.getFileLink()), FileStorage.class);
    }
}
