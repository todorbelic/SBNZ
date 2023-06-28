package com.bank.sbnz.repository;

import com.bank.sbnz.model.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
}
