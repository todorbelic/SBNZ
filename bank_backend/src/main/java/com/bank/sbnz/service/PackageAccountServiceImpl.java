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
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCvcNumber(generateRandomNumber());
        paymentCard.setExpirationDate(LocalDate.now().plusYears(5));
        paymentCardRepository.save(paymentCard);
        bankAccount.setPaymentCard(paymentCard);
        bankAccountRepository.save(bankAccount);
        newPackageAccount.setBankAccount(bankAccount);
        packageAccountRepository.save(newPackageAccount);
        return newPackageAccount;
    }

    public static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000); // Generates a random number between 0 and 999 (exclusive)
        return String.format("%03d", randomNumber); // Formats the number as a three-digit string
    }


}
