package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPurchase {
    private Book book;
    private LocalDate purchaseDate;
}
