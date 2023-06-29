package com.bank.sbnz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditRequestProcessedDTO {
    private Long id;
    private boolean approved;
}
