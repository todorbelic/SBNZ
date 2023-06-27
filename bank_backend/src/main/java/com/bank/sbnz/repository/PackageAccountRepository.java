package com.bank.sbnz.repository;

import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.PackageAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageAccountRepository extends JpaRepository<PackageAccount, Long> {
    Optional<PackageAccount> findById(Long id);
    Optional<PackageAccount> findByBankAccount(BankAccount bankAccount);
}
