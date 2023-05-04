package com.sbnz.sbnz.controller;

import com.sbnz.sbnz.DTO.OrderDTO;
import com.sbnz.sbnz.DTO.ProcessedOrderDTO;
import com.sbnz.sbnz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/getProcessedOrder")
    public ProcessedOrderDTO getProcessedOrder(@RequestBody OrderDTO orderDTO) {
        ProcessedOrderDTO order = orderService.getProcessedOrder(orderDTO);
        return order;
    }

}
