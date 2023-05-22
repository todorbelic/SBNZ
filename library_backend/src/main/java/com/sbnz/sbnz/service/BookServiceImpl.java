package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.model.AppUser;
import com.sbnz.sbnz.model.Author;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Rating;
import com.sbnz.sbnz.repository.AppUserRepository;
import com.sbnz.sbnz.repository.AuthorRepository;
import com.sbnz.sbnz.repository.BookRepository;
import com.sbnz.sbnz.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;
    private final RatingRepository ratingRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, RatingRepository ratingRepository,
                           AppUserRepository appUserRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.ratingRepository = ratingRepository;
        this.appUserRepository = appUserRepository;
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

    @Override
    public Boolean gradeBook(Long bookId, Long userId, Double value) throws IllegalStateException {
        Rating rating = new Rating();
        Optional<AppUser> appUser = appUserRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);
        if(!(appUser.isPresent() && book.isPresent())) {
            throw new IllegalStateException();
        }
        rating.setAppUser(appUser.get());
        rating.setBook(book.get());
        rating.setValue(value);
        if(bookAlreadyRatedByUser(book, appUser)) {
            return false;
        }
        ratingRepository.save(rating);
        book.get().getRatings().add(rating);
        bookRepository.save(book.get());
        return true;
    }

    private boolean bookAlreadyRatedByUser(Optional<Book> book, Optional<AppUser> appUser) {
        for (Rating r : book.get().getRatings()) {
            if (Objects.equals(r.getAppUser().getId(), appUser.get().getId())) {
                return true;
            }
        }
        return false;
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
