package com.bank.sbnz.controller;

import com.bank.sbnz.DTO.CreditRequestDTO;
import com.bank.sbnz.DTO.TransactionDTO;
import com.bank.sbnz.service.CreditService;
import com.bank.sbnz.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/v1/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping(value = "/credit-request-processing")
    public ResponseEntity<Boolean> processCreditRequest(@RequestBody CreditRequestDTO creditRequestDTO) {
        try{
            boolean transactionValid = creditService.shouldApproveCredit(creditRequestDTO);
            return new ResponseEntity<>(transactionValid, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
