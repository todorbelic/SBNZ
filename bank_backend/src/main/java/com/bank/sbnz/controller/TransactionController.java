package com.bank.sbnz.controller;

import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.service.TransactionService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/save-transaction")
    public ResponseEntity addBook(@RequestBody TransactionDTO transactionDTO) throws IOException, GeoIp2Exception {
        boolean transactionValid = transactionService.saveTransaction(transactionDTO);
        if (transactionValid) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}