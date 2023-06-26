package com.bank.sbnz.service;

import com.bank.sbnz.model.BankAccount;

public interface BankAccountService {
    BankAccount getByAccountNumber(String accountNumber);
    void save(BankAccount bankAccount);
}
