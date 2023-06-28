package com.bank.sbnz.service;

import com.bank.sbnz.enums.PackageAccountType;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.PackageAccount;

import java.util.List;

public interface PackageAccountService {

    PackageAccount addPackageAccount(AppUser client, PackageAccountType type);
    List<PackageAccount> findAll();
}
