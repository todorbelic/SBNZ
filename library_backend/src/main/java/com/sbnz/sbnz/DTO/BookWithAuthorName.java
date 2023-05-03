package com.sbnz.sbnz.DTO;

import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookWithAuthorName {

    private Long id;
    private Genre genre;
    private String name;
    private String authorFirstName;
    private String authorLastName;
    private double price;

    public BookWithAuthorName(Book book) {
        this.id = book.getId();
        this.genre = book.getGenre();
        this.name = book.getName();
        this.authorFirstName = book.getAuthor().getFirstName();
        this.authorLastName = book.getAuthor().getFirstName();
        this.price = book.getPrice();
    }
}
