package com.bank.sbnz.model;

import com.bank.sbnz.enums.PackageAccountType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "package_account")
public class PackageAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private PackageAccountType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;

}
