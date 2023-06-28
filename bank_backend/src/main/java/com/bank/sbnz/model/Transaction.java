package com.bank.sbnz.model;

import jakarta.persistence.*;
import lombok.*;
import com.bank.sbnz.enums.TransactionStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private double amount;
    @Column
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "package_account_id", referencedColumnName = "id")
    private PackageAccount packageAccount;
    @Column
    private TransactionStatus transactionStatus;
    @Column
    private String country;

    public Transaction(double amount, LocalDateTime date, PackageAccount packageAccount) {
        this.amount = amount;
        this.date = date;
        this.packageAccount = packageAccount;
    }
}
