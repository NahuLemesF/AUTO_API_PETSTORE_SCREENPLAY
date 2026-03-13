package org.nahulemes.util;

import io.restassured.specification.RequestSpecification;

public final class RequestSpecs {

    private RequestSpecs() {
    }

    public static RequestSpecification json(RequestSpecification request) {
        return request
                .contentType("application/json")
                .accept("application/json");
    }
}
