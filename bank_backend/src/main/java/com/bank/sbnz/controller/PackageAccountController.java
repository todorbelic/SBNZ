package com.bank.sbnz.controller;

import com.bank.sbnz.DTO.NewPackageDTO;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.PackageAccount;
import com.bank.sbnz.repository.AppUserRepository;
import com.bank.sbnz.service.PackageAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/v1/package-account")
public class PackageAccountController {

    private final PackageAccountService packageAccountService;
    private final AppUserRepository appUserRepository;

    public PackageAccountController(PackageAccountService packageAccountService,
                                    AppUserRepository appUserRepository) {
        this.packageAccountService = packageAccountService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping(value = "/create-package")
    public ResponseEntity<PackageAccount> addBook(@RequestBody NewPackageDTO packageDTO) {
        AppUser client = appUserRepository.getReferenceById(packageDTO.getId());
        PackageAccount packageAccount = packageAccountService.addPackageAccount(client, packageDTO.getType());
        if (packageAccount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(packageAccount, HttpStatus.CREATED);
    }
}