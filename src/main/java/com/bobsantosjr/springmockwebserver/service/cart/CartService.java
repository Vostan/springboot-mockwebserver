package com.bobsantosjr.springmockwebserver.service.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CartService {
    private final RestTemplate restTemplate;

    public BigDecimal total(Long id) {
        String uri = "http://localhost:5001/cart/" + id + "/total";
        CartTotal response = restTemplate.getForEntity(uri, CartTotal.class).getBody();
        return response.getAmount();
    }
}
