package com.bobsantosjr.springmockwebserver.service.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.jetbrains.annotations.NotNull

class DefaultMockServer {
    private final MockWebServer server = new MockWebServer()
    private final List<MockServerExpectation> expectations = []

    MockServerExpectation expect() {
        def expectation = new MockServerExpectation()
        this.expectations.add(expectation)
        return expectation
    }

    void start(int port) {
        server.setDispatcher(new Dispatcher() {
            @Override
            MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                def mockRequest = new MockServerRequest(recordedRequest)
                def expectation = expectations.find { mockRequest.matches(it.path) }

                return expectation ? expectation.response : new MockResponse().setResponseCode(404)
            }
        })
        server.start(port)
    }

    void shutdown() {
        server.shutdown()
    }
}
