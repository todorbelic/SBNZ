package com.bank.sbnz.repository;

import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);
    Optional<BankAccount> findByPaymentCard(PaymentCard paymentCard);
}
