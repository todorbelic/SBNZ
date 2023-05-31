package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.enums.Genre;
import com.sbnz.sbnz.model.Author;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecommendedBook {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;
    private double price;
    private LocalDate publishDate;
    private LocalDate addDate;
    private int recommendationPoints;
    private List<Rating> ratingList;

    public RecommendedBook(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.name = book.getName();
        this.genre = book.getGenre();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
        this.addDate = book.getAddDate();
        this.recommendationPoints = 0;
        this.ratingList = book.getRatings();
    }
}
