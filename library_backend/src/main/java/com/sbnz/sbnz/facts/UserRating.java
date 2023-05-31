package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.model.AppUser;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRating {
    private Long id;
    private AppUser appUser;
    private Book book;
    private Double value;
    public UserRating(Rating rating) {
        this.id = rating.getId();
        this.appUser = rating.getAppUser();
        this.book = rating.getBook();
        this.value = rating.getValue();
    }
}