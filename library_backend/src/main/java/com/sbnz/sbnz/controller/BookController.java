package com.sbnz.sbnz.controller;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/v1/books")
public class BookController {

private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<BookWithAuthorName>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookWithAuthorName> booksDTO = new ArrayList<>();

        for(Book b : books) {
            booksDTO.add(new BookWithAuthorName(b));
        }
        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }
}
