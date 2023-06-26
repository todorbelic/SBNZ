package com.bank.sbnz.repository;

import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
