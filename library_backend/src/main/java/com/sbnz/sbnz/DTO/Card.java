package com.sbnz.sbnz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Card {
    private String number;
    private String cvc;
    private String expirationDate;
}
