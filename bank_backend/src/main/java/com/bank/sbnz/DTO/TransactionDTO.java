package com.bank.sbnz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String bankAccountNumber;
    private String ccvNumber;
    private String cardNumber;
    private double amount;
    private LocalDate expirationDate;
}
