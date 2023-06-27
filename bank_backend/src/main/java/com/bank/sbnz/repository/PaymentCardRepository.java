package com.bank.sbnz.repository;

import com.bank.sbnz.model.PackageAccount;
import com.bank.sbnz.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
    Optional<PaymentCard> findByCardNumber(String cardNumber);
}
