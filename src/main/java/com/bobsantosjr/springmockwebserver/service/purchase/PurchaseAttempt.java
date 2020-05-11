package com.bobsantosjr.springmockwebserver.service.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseAttempt {
    private Long id;
    private boolean successful;
}
