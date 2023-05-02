package com.sbnz.sbnz.service;

import com.sbnz.sbnz.model.Order;

public interface IOrderService {
    public Order getDiscount(Order orderRequest);
}
