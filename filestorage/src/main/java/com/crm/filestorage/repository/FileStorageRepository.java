package com.crm.filestorage.repository;

import com.crm.filestorage.entity.FileStorage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileStorageRepository extends MongoRepository<FileStorage, Long> {
    @Override
    List<FileStorage> findAll();
    FileStorage findByFileTitle(String fileTitle);
    Optional<FileStorage> findById(ObjectId id);
}