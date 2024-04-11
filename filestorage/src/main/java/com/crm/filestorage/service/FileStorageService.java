package com.crm.filestorage.service;

import com.crm.filestorage.dto.FileStorageDTO;
import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    public List<FileStorage> findAll() {
        return fileStorageRepository.findAll();
    }

    public FileStorage saveUser(FileStorageDTO fileStorageDTO) {
        return fileStorageRepository.save(makeAFile(fileStorageDTO, false));
    }

    public void deleteUser(FileStorageDTO fileStorageDTO) {
        List<FileStorage> fileStorage = fileStorageRepository.findByFileTitle(fileStorageDTO.getFileTitle());
        //TODO: переделать на Repository
        //fileStorageRepository.deleteById();

//        for(int i=0; i<fileStorage.size();i++) {
//            mongoTemplate.remove(new Query(Criteria.where("_id")
//                            .is(fileStorage.get(i).getId())
//                            .where("fileTitle").is(fileStorage.get(i)
//                            .getFileTitle()).where("fileLink")
//                            .is(fileStorage.get(i).getFileLink())),
//                            FileStorage.class);
//        }
    }

    public void editUser(FileStorageDTO fileStorageDTO) {
//        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(fileStorageDTO.getId())),Update.update("fileTitle",fileStorageDTO.getFileTitle()), FileStorage.class);
//        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(fileStorageDTO.getId())),Update.update("fileLink",fileStorageDTO.getFileLink()), FileStorage.class);
    }

    private FileStorage makeAFile(FileStorageDTO fileStorageDTO, boolean idEnable) {
        FileStorage fileStorage = new FileStorage();
        if (idEnable) {
            fileStorage.setId(fileStorageDTO.getId());
        }
        fileStorage.setFileLink(fileStorageDTO.getFileLink());
        fileStorage.setFileTitle(fileStorageDTO.getFileTitle());

        return fileStorage;
    }
}
