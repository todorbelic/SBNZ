package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
