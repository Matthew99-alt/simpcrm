package com.crm.filestorage.repository;

import com.crm.filestorage.entity.FileStorage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends MongoRepository<FileStorage, ObjectId> {

    FileStorage findByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);

    List<FileStorage> findByOrderIdIsNull();
}