package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.OrderDTO;
import com.sbnz.sbnz.DTO.ProcessedOrderDTO;
import com.sbnz.sbnz.model.Order;

public interface OrderService {
    public ProcessedOrderDTO getProcessedOrder(OrderDTO orderRequest);
    public Order createOrder(Order order);
}
