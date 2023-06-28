package com.bank.sbnz.service;

import com.bank.sbnz.enums.BankAccountType;
import com.bank.sbnz.enums.PackageAccountType;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.BankAccount;
import com.bank.sbnz.model.PackageAccount;
import com.bank.sbnz.model.PaymentCard;
import com.bank.sbnz.repository.AppUserRepository;
import com.bank.sbnz.repository.BankAccountRepository;
import com.bank.sbnz.repository.PackageAccountRepository;
import com.bank.sbnz.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PackageAccountServiceImpl implements PackageAccountService{

    private final PackageAccountRepository packageAccountRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PaymentCardRepository paymentCardRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public PackageAccountServiceImpl(PackageAccountRepository packageAccountRepository, BankAccountRepository bankAccountRepository, PaymentCardRepository paymentCardRepository,
                                     AppUserRepository appUserRepository) {
        this.packageAccountRepository = packageAccountRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public PackageAccount addPackageAccount(AppUser client, PackageAccountType type) {
        PackageAccount newPackageAccount = new PackageAccount();
        newPackageAccount.setAppUser(client);
        newPackageAccount.setType(type);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setMoneyBalance(0);
        bankAccount.setAccountNumber(generateRandomNumber(12));
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCvcNumber(generateRandomNumber(3));
        paymentCard.setExpirationDate(LocalDate.now().plusYears(5));
        paymentCard.setCardNumber(generateRandomNumber(12));
        paymentCardRepository.save(paymentCard);
        bankAccount.setPaymentCard(paymentCard);
        bankAccountRepository.save(bankAccount);
        newPackageAccount.setBankAccount(bankAccount);
        packageAccountRepository.save(newPackageAccount);
        return newPackageAccount;
    }

    public static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generates a random digit between 0 and 9
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    @Override
    public List<PackageAccount> findAll() {
        return packageAccountRepository.findAll();
    }




}
