package com.bank.sbnz.service;

import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.enums.PackageAccountType;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.PackageAccount;
import com.bank.sbnz.model.Transaction;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;

public interface TransactionService {
    boolean saveTransaction(TransactionDTO transactionDTO) throws IOException, GeoIp2Exception;
}
