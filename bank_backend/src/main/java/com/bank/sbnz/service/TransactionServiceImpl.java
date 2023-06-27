package com.bank.sbnz.service;

import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.enums.TransactionStatus;
import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.PaymentCard;
import com.bank.sbnz.model.Transaction;
import com.bank.sbnz.repository.BankAccountRepository;
import com.bank.sbnz.repository.PaymentCardRepository;
import com.bank.sbnz.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final BankAccountService bankAccountService;
    private final TransactionRepository transactionRepository;
    private final PaymentCardRepository paymentCardRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public TransactionServiceImpl(BankAccountService bankAccountService, TransactionRepository transactionRepository,
                                  PaymentCardRepository paymentCardRepository,
                                  BankAccountRepository bankAccountRepository) {
        this.bankAccountService = bankAccountService;
        this.transactionRepository = transactionRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO) {
        Optional<PaymentCard> paymentCard = paymentCardRepository.findByCardNumber(transactionDTO.getCardNumber());
        Optional<BankAccount> bankAccount = bankAccountRepository.findByPaymentCard(paymentCard.get());
        if(bankAccount == null || !transactionCardDetailsValid(transactionDTO, bankAccount.get())){
            throw new RuntimeException(new FindException());
        }
        Transaction transaction = new Transaction(transactionDTO.getAmount(), LocalDateTime.now(), bankAccount.get());
        if(bankAccount.get().getMoneyBalance() - transaction.getAmount() >= 0) {
            bankAccount.get().setMoneyBalance(bankAccount.get().getMoneyBalance() - transaction.getAmount());
            transaction.setTransactionStatus(TransactionStatus.ACCEPTED);
            bankAccountService.save(bankAccount.get());
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
