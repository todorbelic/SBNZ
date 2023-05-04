package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.model.Author;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.repository.AuthorRepository;
import com.sbnz.sbnz.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public Book addBook(BookWithAuthorName bookDTO){
        Book b;
        Book saved;
        if(authorExists(bookDTO)){
            Author a = authorRepository.findByFirstNameAndLastName(bookDTO.getAuthorFirstName(), bookDTO.getAuthorLastName());
            b = new Book(bookDTO.getId(), bookDTO.getName(), a, bookDTO.getGenre(), bookDTO.getPrice());
            saved = bookRepository.save(b);
        } else {
            Author a = new Author();
            a.setFirstName(bookDTO.getAuthorFirstName());
            a.setLastName(bookDTO.getAuthorLastName());
            Author newAuthor = authorRepository.save(a);
            b = new Book(bookDTO.getId(), bookDTO.getName(), newAuthor, bookDTO.getGenre(), bookDTO.getPrice());
            saved = bookRepository.save(b);
        }

        return saved;
    }

    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    public boolean authorExists(BookWithAuthorName bookDTO) {

        List<Author> authors = authorRepository.findAll();

        for(Author a: authors){
            if(a.getFirstName().equalsIgnoreCase(bookDTO.getAuthorFirstName()) && a.getLastName().equalsIgnoreCase(bookDTO.getAuthorLastName())){
                return true;
            }
        }
        return false;
    }


}
