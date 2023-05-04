package com.sbnz.sbnz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    public List<BookWithAuthorName> orderItems;

    public OrderDTO() {}
}
