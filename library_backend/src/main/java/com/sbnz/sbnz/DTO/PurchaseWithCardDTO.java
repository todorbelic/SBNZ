package com.sbnz.sbnz.DTO;
import com.sbnz.sbnz.enums.PaymentMethod;
import com.sbnz.sbnz.model.Address;
public class PurchaseWithCardDTO extends PurchaseDTO{
    public Card card;

    public String ipAddress;
}
