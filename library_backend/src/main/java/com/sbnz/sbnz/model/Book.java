package com.sbnz.sbnz.model;

import com.sbnz.sbnz.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter

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
}