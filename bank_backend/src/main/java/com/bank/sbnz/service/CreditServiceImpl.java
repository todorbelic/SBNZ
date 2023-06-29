package com.bank.sbnz.service;

import com.bank.sbnz.DTO.CreditRequestDTO;
import com.bank.sbnz.DTO.CreditRequestProcessedDTO;
import com.bank.sbnz.enums.CreditRequestStatus;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.CreditRequest;
import com.bank.sbnz.repository.AppUserRepository;
import com.bank.sbnz.repository.CreditRequestRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreditServiceImpl implements CreditService{

    private final AppUserRepository appUserRepository;
    private final CreditRequestRepository creditRequestRepository;
    private final KieSession kieSession;

    public CreditServiceImpl(AppUserRepository appUserRepository, CreditRequestRepository creditRequestRepository, KieSession kieSession) {
        this.appUserRepository = appUserRepository;
        this.creditRequestRepository = creditRequestRepository;
        this.kieSession = kieSession;
    }

    @Override
    public CreditRequestProcessedDTO shouldApproveCredit(CreditRequestDTO creditRequestDto) {
        CreditRequest creditRequest = getCreditRequest(creditRequestDto);
        try{
            AppUser appUser = appUserRepository.findByUsername(creditRequestDto.getClientUsername()).get();
            creditRequest.setClient(appUser);
            kieSession.insert(creditRequest);
            kieSession.fireAllRules();
            creditRequestRepository.save(creditRequest);
            return new CreditRequestProcessedDTO(creditRequest.getId(), creditRequest.isRbeApproval());
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new CreditRequestProcessedDTO();
        }
    }

    @Override
    public void ApproveCredit(Long id) {
        CreditRequest creditRequest = creditRequestRepository.findById(id).get();
        creditRequest.setStatus(CreditRequestStatus.ONGOING);
        creditRequest.setNextPaymentDate(LocalDate.now().plusMonths(1));
        creditRequestRepository.save(creditRequest);
    }

    @Override
    public void RejectCredit(Long id) {
        CreditRequest creditRequest = creditRequestRepository.findById(id).get();
        creditRequest.setStatus(CreditRequestStatus.REJECTED);
        creditRequestRepository.save(creditRequest);
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
        creditRequest.setInstallmentsLeft(creditRequestDto.getNumOfInstallments());
        return creditRequest;
    }
}
