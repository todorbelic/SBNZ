package com.sbnz.sbnz.DTO;
import com.sbnz.sbnz.enums.PaymentMethod;
import com.sbnz.sbnz.model.Address;
public class PurchaseDTO {
    public Long userId;
    public Address address;
    public ProcessedOrderDTO processedOrder;
    public PaymentMethod paymentMethod;

}
