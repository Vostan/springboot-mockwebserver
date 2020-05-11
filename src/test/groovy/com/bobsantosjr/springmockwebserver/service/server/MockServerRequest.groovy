package com.bobsantosjr.springmockwebserver.service.server

import okhttp3.mockwebserver.RecordedRequest

class MockServerRequest {
    String path

    MockServerRequest(RecordedRequest recordedRequest) {
        this.path = recordedRequest.path
    }

    boolean matches(String path) {
        return this.path.matches(path)
    }
}
