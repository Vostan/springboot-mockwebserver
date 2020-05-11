package com.bobsantosjr.springmockwebserver.api.checkout;

import com.bobsantosjr.springmockwebserver.service.cart.CartService;
import com.bobsantosjr.springmockwebserver.service.purchase.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CheckoutController {
    private final CartService cartService;
    private final PurchaseService purchaseService;

    @GetMapping("/{userId}/purchase/{cartId}")
    public ResponseEntity<?> purchase(@PathVariable Long userId, @PathVariable Long cartId){
        BigDecimal totalAmount = cartService.total(cartId);
        boolean purchased = purchaseService.purchase(userId, cartId);

        CheckoutResponse response = new CheckoutResponse(userId, cartId, totalAmount, purchased);
        return purchased ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().build();
    }
}
