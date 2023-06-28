package com.bank.sbnz.model;

import com.bank.sbnz.converter.EmploymentInfoConverter;
import com.bank.sbnz.enums.CreditRequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "credit_request")
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private AppUser client;
    @Column
    private double totalAmount;
    @Column
    private LocalDate minPaymentDeadline;
    @Column
    private LocalDate maxPaymentDeadline;
    @Column
    private CreditRequestStatus status;
    @Convert(converter = EmploymentInfoConverter.class)
    private EmploymentInfo employmentInfo;
    @Column
    private int numOfInstallments;
    @Column
    private double oneInstallmentAmount;
    @Column
    private LocalDate nextPaymentDate; //if approved
}
