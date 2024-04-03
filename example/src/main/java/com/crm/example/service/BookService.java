package com.crm.example.service;

import com.crm.example.entity.dto.BookDTO;
import com.crm.example.entity.Book;
import com.crm.example.reposotory.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());

        return bookRepository.save(book);
    }
}
