package com.sbnz.sbnz.controller;

import com.sbnz.sbnz.DTO.PurchaseDTO;
import com.sbnz.sbnz.DTO.PurchaseWithCardDTO;
import com.sbnz.sbnz.model.Purchase;
import com.sbnz.sbnz.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Boolean canAfford = true;
        if(!canAfford){
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