package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Order;
import com.sbnz.sbnz.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
