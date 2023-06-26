package com.bank.sbnz.service;

import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.enums.TransactionStatus;
import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.Transaction;
import com.bank.sbnz.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.module.FindException;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final BankAccountService bankAccountService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(BankAccountService bankAccountService, TransactionRepository transactionRepository) {
        this.bankAccountService = bankAccountService;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO) {
        BankAccount bankAccount = bankAccountService.getByAccountNumber(transactionDTO.getBankAccountNumber());
        if(bankAccount == null || !transactionCardDetailsValid(transactionDTO, bankAccount)){
            throw new RuntimeException(new FindException());
        }
        Transaction transaction = new Transaction(transactionDTO.getAmount(), LocalDateTime.now(), bankAccount);
        if(bankAccount.getMoneyBalance() - transaction.getAmount() >= 0) {
            bankAccount.setMoneyBalance(bankAccount.getMoneyBalance() - transaction.getAmount());
            transaction.setTransactionStatus(TransactionStatus.ACCEPTED);
            bankAccountService.save(bankAccount);
            transactionRepository.save(transaction);
            return true;
        }
        transaction.setTransactionStatus(TransactionStatus.REJECTED);
        transactionRepository.save(transaction);
        return false;
    }

    private boolean transactionCardDetailsValid(TransactionDTO transactionDTO, BankAccount bankAccount) {
        if(bankAccount == null) {
            return false;
        }
        return (bankAccount.getPaymentCard().getCardNumber().equals(transactionDTO.getCardNumber()) &&
                bankAccount.getPaymentCard().getCvcNumber().equals(transactionDTO.getCcvNumber()) &&
                bankAccount.getPaymentCard().getExpirationDate().equals(transactionDTO.getExpirationDate()));
    }
}
