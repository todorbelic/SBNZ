package com.sbnz.sbnz.model;

import com.sbnz.sbnz.converter.AddressConverter;
import com.sbnz.sbnz.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser user;
    @Convert(converter = AddressConverter.class)
    private Address deliveryAddress;
    @Column
    private PaymentMethod paymentMethod;
    @Column
    private LocalDate date;
}
