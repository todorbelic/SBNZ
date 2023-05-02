package com.sbnz.sbnz.service;

import com.sbnz.sbnz.model.Order;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{


    private KieContainer kieContainer;

    @Autowired
    public OrderService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Order getDiscount(Order orderRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderRequest;
    }
}
