package com.sbnz.sbnz.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Book> books;
    @Column
    private int popularity;

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.setBooks(new ArrayList<>());
        this.popularity = 0;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity() {
        int sum = 0;
        for(Book b: books){
            for (Rating r: b.ratings){
                sum += r.getValue();
            }
        }
        this.popularity = sum;
    }
}
