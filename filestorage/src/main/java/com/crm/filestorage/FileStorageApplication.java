package com.crm.filestorage;

import com.crm.filestorage.entity.FileStorage;
import com.crm.filestorage.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageApplication {

	@Autowired
	private FileStorageRepository fileStorageRepository;

	public static void main(String[] args) {
		SpringApplication.run(FileStorageApplication.class, args);
	}
}
