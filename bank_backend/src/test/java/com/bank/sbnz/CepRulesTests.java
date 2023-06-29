package com.bank.sbnz;

import com.bank.sbnz.enums.PackageAccountType;
import com.bank.sbnz.enums.Role;
import com.bank.sbnz.enums.TransactionStatus;
import com.bank.sbnz.events.TransactionEvent;
import com.bank.sbnz.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.kie.api.runtime.KieSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CEPRulesTest {
    private final KieSession kieSession;

    private AppUser testAppUser;
    private PaymentCard testPaymentCard;
    private BankAccount testBankAccount;
    private PackageAccount testPackageAccount;
    private List<Transaction> transactions;

    @Autowired
    public CEPRulesTest(KieSession kieSession){
        this.kieSession = kieSession;
        this.testAppUser = new AppUser(1l, "username", "12312", "first", "last", Role.USER, LocalDate.now(), 33, null, null);
        this.testPaymentCard = new PaymentCard(1l, LocalDate.now().plusYears(1), "130", "123123123123");
        this.testBankAccount = new BankAccount(1l, "1", 1000, this.testPaymentCard);
        this.testPackageAccount = new PackageAccount(1l, PackageAccountType.CHECKING, this.testAppUser, this.testBankAccount);
        this.transactions = getTransactions();
    }

    private List<Transaction> getTransactions(){
        Transaction transaction1 = new Transaction(1l, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "RS");
        Transaction transaction2 = new Transaction(2l, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "BH");
        List<Transaction> listOfTransactions = new ArrayList<Transaction>();
        listOfTransactions.add(transaction1);
        listOfTransactions.add(transaction2);
        return listOfTransactions;
    }

    @Test
    public void testBigNumberOfTransactions() {
        Transaction transaction1 = new Transaction(null, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "RS");
        Transaction transaction2 = new Transaction(null, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "RS");
        TransactionEvent transactionEvent1 = new TransactionEvent(transaction1);
        transactionEvent1.setExecutionTime(new Date());
        TransactionEvent transactionEvent2 = new TransactionEvent(transaction2);
        transactionEvent2.setExecutionTime(new Date());

        kieSession.insert(this.transactions);
        kieSession.insert(transactionEvent1);
        kieSession.insert(transactionEvent2);
        kieSession.fireAllRules();
    }

    @Test
    public void testDifferentCountryMultipleTransactions(){
        Transaction transaction1 = new Transaction(null, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "RS");
        Transaction transaction2 = new Transaction(null, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "BH");
        TransactionEvent transactionEvent1 = new TransactionEvent(transaction1);
        transactionEvent1.setExecutionTime(new Date());
        TransactionEvent transactionEvent2 = new TransactionEvent(transaction2);
        transactionEvent2.setExecutionTime(new Date());

        kieSession.insert(this.transactions);
        kieSession.insert(transactionEvent1);
        kieSession.insert(transactionEvent2);
        kieSession.fireAllRules();
    }

    @Test
    public void testUnknownCountryTransaction(){
        Transaction transaction1 = new Transaction(null, 2000, LocalDateTime.now(), this.testPackageAccount, TransactionStatus.ACCEPTED, "US");
        TransactionEvent transactionEvent1 = new TransactionEvent(transaction1);
        transactionEvent1.setExecutionTime(new Date());

        kieSession.insert(this.transactions);
        kieSession.insert(transactionEvent1);
        kieSession.fireAllRules();
    }

    @Test
    public void testUnusualAmountAtOddHours(){
        LocalDateTime oddHours = LocalDateTime.now().withHour(23);
        Transaction transaction1 = new Transaction(null, 20000, oddHours, this.testPackageAccount, TransactionStatus.ACCEPTED, "US");
        kieSession.insert(transaction1);
        kieSession.fireAllRules();
    }

    @Test
    public void testUnusualTransactionAmountComparedToAverage(){
        Transaction transaction1 = new Transaction(null, 20000, LocalDateTime.now(), this.testPackageAccount, null, "US");
        kieSession.insert(this.transactions);
        kieSession.insert(transaction1);
        kieSession.fireAllRules();
    }
}
