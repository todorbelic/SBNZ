package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.facts.*;
import com.sbnz.sbnz.model.*;
import com.sbnz.sbnz.repository.*;
import com.sbnz.sbnz.enums.Genre;
import com.sbnz.sbnz.model.AppUser;
import com.sbnz.sbnz.model.Author;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Rating;
import com.sbnz.sbnz.repository.AppUserRepository;
import com.sbnz.sbnz.repository.AuthorRepository;
import com.sbnz.sbnz.repository.BookRepository;
import com.sbnz.sbnz.repository.RatingRepository;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mvel2.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RatingRepository ratingRepository;
    private final AppUserRepository appUserRepository;
    private final PurchaseRepository purchaseRepository;
    private final KieContainer kieContainer;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, PurchaseRepository purchaseRepository,
            RatingRepository ratingRepository,
            AppUserRepository appUserRepository, KieContainer kieContainer) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.ratingRepository = ratingRepository;
        this.appUserRepository = appUserRepository;
        this.kieContainer = kieContainer;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(BookWithAuthorName bookDTO) {
        Book b;
        Book saved;
        if (authorExists(bookDTO)) {
            Author a = authorRepository.findByFirstNameAndLastName(bookDTO.getAuthorFirstName(),
                    bookDTO.getAuthorLastName());
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
        if (!(appUser.isPresent() && book.isPresent())) {
            throw new IllegalStateException();
        }
        rating.setAppUser(appUser.get());
        rating.setBook(book.get());
        rating.setValue(value);
        if (bookAlreadyRatedByUser(book, appUser)) {
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

        for (Author a : authors) {
            if (a.getFirstName().equalsIgnoreCase(bookDTO.getAuthorFirstName())
                    && a.getLastName().equalsIgnoreCase(bookDTO.getAuthorLastName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> GetNonAuthUserBookRecommendation() {
        KieSession kieSession = kieContainer.newKieSession();
        List<Book> books = bookRepository.findAll();
        kieSession.insert(books);
        kieSession.fireAllRules();
        kieSession.dispose();
        return books.stream()
                .filter(Book::isRecommended)
                .limit(10)
                .collect(Collectors.toList());
    }


    public List<Book> GetOlderUserBookRecommendation(Long userID) {
        Optional<AppUser> loggedUser = appUserRepository.findById(userID);
        List<Purchase> userOrders = purchaseRepository.findAllByUserId(userID);
        List<AppUser> allUsers = appUserRepository.findAll();

        for(AppUser user : allUsers)  {
            List<Rating> ratings = ratingRepository.findAllByAppUserId(user.getId());
            user.setRatings(ratings);
        }

        LoggedInUser loggedInUser = new LoggedInUser(loggedUser.get());
        List<UserPurchase> userPurchases = new ArrayList<>();
        for(Purchase purchase : userOrders) {
            for(OrderItem orderItem : purchase.getOrder().getOrderItems()) {
                userPurchases.add(new UserPurchase(orderItem.getBook(), purchase.getDate()));
            }
        }

        loggedInUser.setPurchases(userPurchases);
        List<Book> allBooks = bookRepository.findAll();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(loggedInUser);
        RecommendedBookList recommendedList = new RecommendedBookList();
        kieSession.insert(recommendedList);
        kieSession.insert(allUsers);
        kieSession.insert(allBooks);
        kieSession.getAgenda().getAgendaGroup("older_user").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        List<Book> books = new ArrayList<>();
        for(RecommendedBook recBook : recommendedList.getRecommendedBooks()) {
            books.add(new Book(recBook));
        }
        return books;
    }


    public List<Book> GetAuthUserBookRecommendation(Long userId){
        List<Rating> ratings = ratingRepository.findAllByAppUserId(userId);
        List<Book> books = bookRepository.findAll();
        if(ratings.size() < 10) {
            AppUser u = appUserRepository.getById(userId);

            if (u.getFavouriteGenre() == null) {
                return GetNonAuthUserBookRecommendation();
            }

            KieSession kieSession = kieContainer.newKieSession();
            List<Author> authors = authorRepository.findAll();

            for (Author a : authors) {
                if (a.getBooks().isEmpty() || a.getBooks() == null) {
                    List<Book> authorsBooks = bookRepository.findAllByAuthorId(a.getId());
                    a.setBooks(authorsBooks);
                    authorRepository.save(a);
                }
            }

            Genre g = u.getFavouriteGenre();
            kieSession.insert(authors);
            kieSession.insert(books);
            kieSession.insert(g);

            kieSession.setGlobal("zanr", g);
            kieSession.getAgenda().getAgendaGroup("test").setFocus();

            kieSession.fireAllRules();
            kieSession.dispose();


            return books.stream().limit(10).collect(Collectors.toList());
        }
        return GetOlderUserBookRecommendation(userId).stream().limit(20).toList();
    }
}

