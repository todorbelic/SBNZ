package com.sbnz.sbnz.controller;


import com.sbnz.sbnz.service.IOrderService;
import com.sbnz.sbnz.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order getOrder(@RequestBody Order order) {
        Order oder = orderService.getDiscount(order);
        return oder;
    }

}
