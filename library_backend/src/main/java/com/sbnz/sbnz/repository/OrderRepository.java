package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
