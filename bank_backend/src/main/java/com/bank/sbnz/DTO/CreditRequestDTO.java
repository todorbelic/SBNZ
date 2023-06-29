package com.bank.sbnz.DTO;

import com.bank.sbnz.enums.CreditRequestStatus;
import com.bank.sbnz.model.AppUser;
import com.bank.sbnz.model.EmploymentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDTO {
    private String clientUsername;
    private double totalAmount;
    private LocalDate minPaymentDeadline;
    private LocalDate maxPaymentDeadline;
    private EmploymentInfo employmentInfo;
    private int numOfInstallments;
    private double oneInstallmentAmount;
}
