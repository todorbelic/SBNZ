package com.bank.sbnz.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Integer moneyBalance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_card_id", referencedColumnName = "id")
    private PaymentCard paymentCard;



}
