package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Order;
import com.sbnz.sbnz.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUserId(Long appUserId);
}
