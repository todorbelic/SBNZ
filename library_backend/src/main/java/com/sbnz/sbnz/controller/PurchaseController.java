package com.sbnz.sbnz.controller;

import com.sbnz.sbnz.DTO.PurchaseDTO;
import com.sbnz.sbnz.DTO.PurchaseWithCardDTO;
import com.sbnz.sbnz.DTO.TransactionDTO;
import com.sbnz.sbnz.model.Purchase;
import com.sbnz.sbnz.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/createPurchase")
    public  ResponseEntity<Purchase> getProcessedOrder(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.createPurchase(purchaseDTO);
        if(purchase == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/createPurchaseCard")
    public  ResponseEntity<Purchase> getProcessedOrder(@RequestBody PurchaseWithCardDTO purchaseDTO) {
        //Purchase purchase = purchaseService.createPurchase(purchaseDTO);
        //u drugi bek treba da se psaolje i racun na koji se salje novac (od biblioteke)
        String url = "http://localhost:8083/api/v1/transaction/save-transaction";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        TransactionDTO transactionDTO = new TransactionDTO(purchaseDTO.card.getBankAccountNumber(), purchaseDTO.card.getCvc(), purchaseDTO.card.getNumber(),
                                                           purchaseDTO.processedOrder.totalPrice, LocalDate.parse(purchaseDTO.card.getExpirationDate(), DateTimeFormatter.ISO_DATE));
        HttpEntity<TransactionDTO> requestEntity = new HttpEntity<>(transactionDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
        if(!response.getStatusCode().is2xxSuccessful()){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            Purchase purchase = purchaseService.createPurchase(purchaseDTO);
            if (purchase == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
    }

}