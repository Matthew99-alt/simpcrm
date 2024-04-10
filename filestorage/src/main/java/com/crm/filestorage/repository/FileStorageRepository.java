package com.crm.filestorage.repository;

import com.crm.filestorage.entity.FileStorage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileStorageRepository extends MongoRepository<FileStorage, ObjectId> {
    @Override
    List<FileStorage> findAll();

    List<FileStorage> findByFileTitle(String title);
}