package com.sbnz.sbnz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Book book;
    @Column
    private int quantity;
    @Column
    private double price;
    @Column
    private double discount;

    @ManyToOne
    private Order order;
}
