package com.crm.example.reposotory;

import com.crm.example.entity.Book;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
    @Override
    List<Book> findAll();
}
