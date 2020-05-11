package com.bobsantosjr.springmockwebserver.api.checkout

import com.bobsantosjr.springmockwebserver.service.purchase.PurchaseAttempt
import com.bobsantosjr.springmockwebserver.service.server.DefaultMockServer
import com.bobsantosjr.springmockwebserver.service.cart.CartTotal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckoutControllerPurchaseSpec extends Specification {
    @Autowired
    CheckoutController controller

    List<DefaultMockServer> servers = []

    def setup() {
        setupCartService()
        setupPurchaseService()
    }

    def "should be successful checkout"() {
        when:
        def response = controller.purchase(123, 234)

        then:
        response.statusCode.value() == 200
        response.getBody() instanceof CheckoutResponse
        CheckoutResponse checkoutResponse = (CheckoutResponse) response.body
        checkoutResponse.amount == 100
        checkoutResponse.successful
    }

    void setupCartService() {
        DefaultMockServer server = new DefaultMockServer()

        server.expect().withPath("/cart/\\d+/total")
            .andReturn(200, new CartTotal(1L, BigDecimal.valueOf(100)))

        server.start(5001)
        servers.add(server)
    }

    void setupPurchaseService() {
        DefaultMockServer server = new DefaultMockServer()

        server.expect().withPath("/purchase/\\d+/\\d+")
                .andReturn(200, new PurchaseAttempt(1, true))

        server.start(5002)
        servers.add(server)
    }

    def cleanup() {
        servers.each { it.shutdown() }
    }
}
