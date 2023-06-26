package com.bank.sbnz.model;

import jakarta.persistence.*;
import lombok.*;
import com.bank.sbnz.enums.TransactionStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private double amount;
    @Column
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;
    @Column
    private TransactionStatus transactionStatus;

    public Transaction(double amount, LocalDateTime date, BankAccount bankAccount) {
        this.amount = amount;
        this.date = date;
        this.bankAccount = bankAccount;
    }
}
