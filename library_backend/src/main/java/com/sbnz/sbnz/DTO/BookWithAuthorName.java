package com.sbnz.sbnz.DTO;

import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.enums.Genre;
import com.sbnz.sbnz.model.OrderItem;
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
    public int quantity;
    public double discount;
    public double total;

    public BookWithAuthorName(Book book) {
        this.id = book.getId();
        this.genre = book.getGenre();
        this.name = book.getName();
        this.authorFirstName = book.getAuthor().getFirstName();
        this.authorLastName = book.getAuthor().getLastName();
        this.price = book.getPrice();
    }

    public BookWithAuthorName(OrderItem item) {
        this.id = item.getBook().getId();
        this.genre = item.getBook().getGenre();
        this.name = item.getBook().getName();
        this.authorFirstName = item.getBook().getAuthor().getFirstName();
        this.authorLastName = item.getBook().getAuthor().getLastName();
        this.total = item.getPrice();
        this.price = item.getBook().getPrice();
        this.quantity = item.getQuantity();
        this.discount = item.getDiscount();
    }
}
