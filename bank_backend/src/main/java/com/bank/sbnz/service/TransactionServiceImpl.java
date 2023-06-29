package com.bank.sbnz.service;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.enums.TransactionStatus;
import com.bank.sbnz.events.MyEvent;
import com.bank.sbnz.events.TransactionEvent;
import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.PackageAccount;
import com.bank.sbnz.model.PaymentCard;
import com.bank.sbnz.model.Transaction;
import com.bank.sbnz.repository.BankAccountRepository;
import com.bank.sbnz.repository.PackageAccountRepository;
import com.bank.sbnz.repository.PaymentCardRepository;
import com.bank.sbnz.repository.TransactionRepository;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.lang.module.FindException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final BankAccountService bankAccountService;
    private final TransactionRepository transactionRepository;
    private final PaymentCardRepository paymentCardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final KieSession kieSession;
    private final PackageAccountRepository packageAccountRepository;


    @Autowired
    public TransactionServiceImpl(BankAccountService bankAccountService,KieSession kieSession, TransactionRepository transactionRepository,
                                  PaymentCardRepository paymentCardRepository,
                                  BankAccountRepository bankAccountRepository,
                                  PackageAccountRepository packageAccountRepository) {
        this.bankAccountService = bankAccountService;
        this.kieSession = kieSession;
        this.transactionRepository = transactionRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.packageAccountRepository = packageAccountRepository;
    }


    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO) throws IOException, GeoIp2Exception {
        Optional<PaymentCard> paymentCard = paymentCardRepository.findByCardNumber(transactionDTO.getCardNumber());
        Optional<BankAccount> bankAccount = bankAccountRepository.findByPaymentCard(paymentCard.get());
        Optional<PackageAccount> packageAccount = packageAccountRepository.findByBankAccount(bankAccount.get());
        if(bankAccount == null || !transactionCardDetailsValid(transactionDTO, bankAccount.get())){
            throw new RuntimeException(new FindException());
        }
        Transaction transaction = new Transaction(transactionDTO.getAmount(), LocalDateTime.now(), packageAccount.get());
        String country = GetCountryFromIpAddress(transactionDTO);
        transaction.setCountry(country);
        TransactionEvent transactionEvent = new TransactionEvent(transaction);
        transactionEvent.setExecutionTime(new Date());
        kieSession.insert(transaction);
        List<Transaction> transactions = transactionRepository.findAll();
        kieSession.insert(transactionRepository.findAll());
        kieSession.insert(transactionEvent);
        kieSession.fireAllRules();
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

    private String GetCountryFromIpAddress(TransactionDTO transactionDTO) throws IOException, GeoIp2Exception {
        String databasePath = "src/main/resources/ipdb/GeoLite2-Country.mmdb";
        DatabaseReader reader = new DatabaseReader.Builder(new File(databasePath)).build();
        String ipAddress = transactionDTO.getIpAddress(); // Replace with the IP address you want to geolocate
        return reader.country(InetAddress.getByName(ipAddress)).getCountry().getIsoCode();
    };

    private boolean transactionCardDetailsValid(TransactionDTO transactionDTO, BankAccount bankAccount) {
        if(bankAccount == null) {
            return false;
        }
        return (bankAccount.getPaymentCard().getCardNumber().equals(transactionDTO.getCardNumber()) &&
                bankAccount.getPaymentCard().getCvcNumber().equals(transactionDTO.getCcvNumber()) &&
                bankAccount.getPaymentCard().getExpirationDate().equals(transactionDTO.getExpirationDate()));
    }
}
