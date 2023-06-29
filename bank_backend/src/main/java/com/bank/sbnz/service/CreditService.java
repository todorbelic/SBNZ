package com.bank.sbnz.service;

import com.bank.sbnz.DTO.CreditRequestDTO;
import com.bank.sbnz.DTO.CreditRequestProcessedDTO;
import com.bank.sbnz.model.CreditRequest;

public interface CreditService {
    CreditRequestProcessedDTO shouldApproveCredit(CreditRequestDTO creditRequest);

    void ApproveCredit(Long id);

    void RejectCredit(Long id);
}
