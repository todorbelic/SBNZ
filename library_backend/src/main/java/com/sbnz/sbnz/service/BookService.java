package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book addBook(BookWithAuthorName bookDTO);
    public Book getById(Long id);
}
