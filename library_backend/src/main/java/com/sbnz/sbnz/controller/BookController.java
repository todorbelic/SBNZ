package com.sbnz.sbnz.controller;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.DTO.RatingDTO;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        for (Book b : books) {
            booksDTO.add(new BookWithAuthorName(b));
        }
        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity<BookWithAuthorName> addBook(@RequestBody BookWithAuthorName bookDTO) {
        Book newBook = bookService.addBook(bookDTO);
        if (newBook == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BookWithAuthorName book = new BookWithAuthorName(newBook);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping(value = "/gradeBook")
    public ResponseEntity<RatingDTO> gradeBook(@RequestBody RatingDTO ratingDTO) {
        if (bookService.gradeBook(ratingDTO.getBookId(), ratingDTO.getUserId(), ratingDTO.getValue())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/non-auth-recommendations")
    public ResponseEntity<List<BookWithAuthorName>> GetNonAuthUserBookRecommendation() {
        List<Book> books = bookService.GetNonAuthUserBookRecommendation();
        List<BookWithAuthorName> booksDTO = new ArrayList<>();

        for (Book b : books) {
            booksDTO.add(new BookWithAuthorName(b));
        }
        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }


    /*@GetMapping(value = "/auth-recommendations")
    public ResponseEntity<List<BookWithAuthorName>> GetAuthUserBookRecommendation() {
        List<Book> books = bookService.GetAuthUserBookRecommendation(12l);
        List<BookWithAuthorName> booksDTO = new ArrayList<>();

        for (Book b : books) {
            booksDTO.add(new BookWithAuthorName(b));
        }
        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }*/

    @GetMapping(value = "/auth-user-recommendations/{userId}")
    public ResponseEntity<List<BookWithAuthorName>> getAuthUserBookRecommendation(@PathVariable("userId") Long userId){
        List<Book> books = bookService.GetAuthUserBookRecommendation(userId);
        List<BookWithAuthorName> booksDTO = new ArrayList<>();
        for (Book b : books) {
            booksDTO.add(new BookWithAuthorName(b));
        }
        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }
}
