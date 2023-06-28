package com.bank.sbnz.service;

import com.bank.sbnz.DTO.CreditRequestDTO;
import com.bank.sbnz.model.CreditRequest;

public interface CreditService {
    boolean shouldApproveCredit(CreditRequestDTO creditRequest);
}
