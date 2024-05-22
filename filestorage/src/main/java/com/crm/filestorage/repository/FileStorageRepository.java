package com.crm.filestorage.repository;

import com.crm.filestorage.entity.FileStorage;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
FileStorageRepository extends MongoRepository<FileStorage, ObjectId> {

    FileStorage findByOrderId(Long orderId);

}