package com.crm.example.controller;

import com.crm.example.entity.dto.BookDTO;
import com.crm.example.entity.Book;
import com.crm.example.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/save")
    public Book saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }
}
