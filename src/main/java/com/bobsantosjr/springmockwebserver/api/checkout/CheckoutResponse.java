package com.bobsantosjr.springmockwebserver.api.checkout;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CheckoutResponse {
    Long userId;
    Long cartId;
    BigDecimal amount;
    boolean successful;
}
