package com.bank.sbnz.service;

import com.bank.sbnz.DTO.CreditRequestDTO;
import com.bank.sbnz.enums.CreditRequestStatus;
import com.bank.sbnz.model.CreditRequest;
import com.bank.sbnz.repository.AppUserRepository;
import com.bank.sbnz.repository.CreditRequestRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService{

    private final KieContainer kieContainer;
    private final AppUserRepository appUserRepository;
    private final CreditRequestRepository creditRequestRepository;

    public CreditServiceImpl(KieContainer kieContainer, AppUserRepository appUserRepository, CreditRequestRepository creditRequestRepository) {
        this.kieContainer = kieContainer;
        this.appUserRepository = appUserRepository;
        this.creditRequestRepository = creditRequestRepository;
    }

    @Override
    public boolean shouldApproveCredit(CreditRequestDTO creditRequestDto) {
        CreditRequest creditRequest = getCreditRequest(creditRequestDto);
        KieSession kieSession = kieContainer.newKieSession();
        creditRequest.setClient(appUserRepository.findByUsername(creditRequestDto.getClientUsername()).get());
        kieSession.insert(creditRequest);
        kieSession.fireAllRules();
        System.out.println(creditRequest.isRbeApproval());
        kieSession.dispose();
        creditRequestRepository.save(creditRequest);
        return creditRequest.isRbeApproval();
    }

    private static CreditRequest getCreditRequest(CreditRequestDTO creditRequestDto) {
        CreditRequest creditRequest = new CreditRequest(creditRequestDto.getEmploymentInfo(),
                                                        creditRequestDto.getMaxPaymentDeadline(),
                                                        creditRequestDto.getOneInstallmentAmount(),
                                                        creditRequestDto.getMinPaymentDeadline(),
                                                        creditRequestDto.getNumOfInstallments(),
                                                        creditRequestDto.getTotalAmount());
        creditRequest.setOneInstallmentAmount(creditRequestDto.getTotalAmount() / creditRequestDto.getNumOfInstallments());
        creditRequest.setStatus(CreditRequestStatus.PENDING);
        return creditRequest;
    }
}
