package com.sbnz.sbnz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Address {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String number;
}
