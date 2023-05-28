package com.sbnz.sbnz.model;

import com.sbnz.sbnz.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    @Column
    private Genre genre;
    @Column
    private double price;
    @Column
    private boolean recommended;
    @Column
    private LocalDate addDate;
    @Column
    private LocalDate publishDate;
    @Column
    private String recommendation;

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Rating> ratings;

    public Book(Long id, String name, Author a, Genre genre, double price) {
        this.setId(id);
        this.setName(name);
        this.setAuthor(a);
        this.setGenre(genre);
        this.setPrice(price);
        this.setRatings(new ArrayList<>());
    }

    public Book(Long id, String name, Author author, Genre genre, double price, boolean recommended, LocalDate addDate, LocalDate publishDate, String recommendation) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.recommended = recommended;
        this.addDate = addDate;
        this.publishDate = publishDate;
        this.recommendation = recommendation;
        this.setRatings(new ArrayList<>());
    }
}