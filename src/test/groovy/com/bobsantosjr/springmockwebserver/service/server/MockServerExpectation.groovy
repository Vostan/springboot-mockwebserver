package com.bobsantosjr.springmockwebserver.service.server

import groovy.json.JsonOutput
import okhttp3.mockwebserver.MockResponse

class MockServerExpectation {
    String path
    MockResponse response

    MockServerExpectation withPath(String path) {
        this.path = path
        return this
    }

    MockServerExpectation andReturn(int statusCode, Object response) {
        this.response = new MockResponse().setResponseCode(statusCode)
            .setBody(JsonOutput.toJson(response))
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
        return this
    }
}
