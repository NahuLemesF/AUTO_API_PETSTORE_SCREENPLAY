package org.nahulemes.questions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseStatusCode implements Question<Integer> {

    public static ResponseStatusCode was() {
        return new ResponseStatusCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        Response response = SerenityRest.lastResponse();
        return response.statusCode();
    }
}
