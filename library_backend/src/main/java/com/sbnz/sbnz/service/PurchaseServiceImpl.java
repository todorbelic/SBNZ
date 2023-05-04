package com.sbnz.sbnz.service;

import com.sbnz.sbnz.DTO.PurchaseDTO;
import com.sbnz.sbnz.mapper.OrderMapper;
import com.sbnz.sbnz.model.Order;
import com.sbnz.sbnz.model.Purchase;
import com.sbnz.sbnz.repository.AppUserRepository;
import com.sbnz.sbnz.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final BookService bookService;
    private final OrderServiceImpl orderService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, BookService bookService, OrderServiceImpl orderService, AppUserRepository appUserRepository) {
        this.purchaseRepository = purchaseRepository;
        this.bookService = bookService;
        this.orderService = orderService;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
        Order order = OrderMapper.processedOrderDtoToOrder(purchaseDTO.processedOrder);
        order.getOrderItems().forEach(orderItem -> orderItem.setBook(bookService.getById(orderItem.getBook().getId())));
        Order savedOrder = orderService.createOrder(order);
        Purchase purchase = new Purchase();
        purchase.setOrder(savedOrder);
        purchase.setUser(appUserRepository.getById(purchaseDTO.userId));
        purchase.setDeliveryAddress(purchaseDTO.address);
        purchase.setPaymentMethod(purchaseDTO.paymentMethod);
        return purchaseRepository.save(purchase);
    }
}
