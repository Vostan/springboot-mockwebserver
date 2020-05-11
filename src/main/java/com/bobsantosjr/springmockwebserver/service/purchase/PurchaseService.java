package com.bobsantosjr.springmockwebserver.service.purchase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final RestTemplate restTemplate;

    public boolean purchase(Long userId, Long cartId) {
        String uri = "http://localhost:5002/purchase/" + userId + "/" + cartId;
        PurchaseAttempt response = restTemplate.getForEntity(uri, PurchaseAttempt.class).getBody();
        return response.isSuccessful();
    }
}
