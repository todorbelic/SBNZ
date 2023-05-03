package com.sbnz.sbnz.DTO;

import com.sbnz.sbnz.enums.Genre;

public class AddBookDTO {
    private Genre genre;
    private String name;
    private String authorFirstName;
    private String authorLastName;
    private double price;

    public AddBookDTO(Genre genre, String name, String authorFirstName, String authorLastName, double price) {
        this.genre = genre;
        this.name = name;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.price = price;
    }
}
