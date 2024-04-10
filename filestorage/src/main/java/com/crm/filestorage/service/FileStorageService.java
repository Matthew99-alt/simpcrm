package com.crm.filestorage.service;

import com.crm.filestorage.config.FileStorageConfig;
import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {
    public final FileStorageRepository fileStorageRepository;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    public List<FileStorage> findAll() {
        return fileStorageRepository.findAll();
    }

    private FileStorage makeAFile(FileStorageDTO fileStorageDTO, boolean idEnable){
        FileStorage fileStorage = new FileStorage();
        if(idEnable){
            fileStorage.setId(new ObjectId(String.valueOf(fileStorageDTO.getId())));
        }
        fileStorage.setFileLink(fileStorageDTO.getFileLink());
        fileStorage.setFileTitle(fileStorageDTO.getFileTitle());

        return fileStorage;
    }



    public FileStorage saveUser(FileStorageDTO fileStorageDTO) {
        return fileStorageRepository.save(makeAFile(fileStorageDTO, false));
    }
    public void deleteUser(FileStorageDTO fileStorageDTO) {
        FileStorageConfig fileStorageConfig = new FileStorageConfig();
        MongoTemplate mongoTemplate = new MongoTemplate(fileStorageConfig.mongoClient(),"test");
        mongoTemplate.remove(new Query(Criteria.where("id").is(fileStorageDTO.getId()).where("fileTitle").is(fileStorageDTO.getFileTitle()).where("fileLink").is(fileStorageDTO.getFileLink())), FileStorage.class);
    }

    public FileStorageDTO editUser(FileStorageDTO fileStorageDTO){
        FileStorageConfig fileStorageConfig = new FileStorageConfig();
        MongoTemplate mongoTemplate = new MongoTemplate(fileStorageConfig.mongoClient(),"test");
        FileStorage fileStorage = fileStorageRepository.findByFileTitle(fileStorageDTO.getFileTitle());
        return fileStorageDTO;
    }
}
