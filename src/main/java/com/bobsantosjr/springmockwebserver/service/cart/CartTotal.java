package com.bobsantosjr.springmockwebserver.service.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartTotal {
    private Long id;
    private BigDecimal amount;
}
