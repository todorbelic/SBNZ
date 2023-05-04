package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.DTO.PurchaseDTO;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Purchase;

public interface PurchaseService {
    Purchase createPurchase(PurchaseDTO purchaseDTO);
}
