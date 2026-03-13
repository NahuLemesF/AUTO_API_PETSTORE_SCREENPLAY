package org.nahulemes.questions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseFieldValue implements Question<String> {

    private final String jsonPath;

    public ResponseFieldValue(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static ResponseFieldValue fromBody(String jsonPath) {
        return new ResponseFieldValue(jsonPath);
    }

    @Override
    public String answeredBy(Actor actor) {
        Response response = SerenityRest.lastResponse();
        return response.jsonPath().getString(jsonPath);
    }
}
