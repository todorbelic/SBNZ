package com.sbnz.sbnz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedOrderDTO {
    public List<BookWithAuthorName> orderItems;
    public double totalPrice;
    public double discount;
}
