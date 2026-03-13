package org.nahulemes.questions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.nahulemes.models.UserData;

import java.util.Objects;

public class UserResponseMatchesData implements Question<Boolean> {

    private final UserData expectedUser;

    public UserResponseMatchesData(UserData expectedUser) {
        this.expectedUser = expectedUser;
    }

    public static UserResponseMatchesData forUser(UserData expectedUser) {
        return new UserResponseMatchesData(expectedUser);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Response response = SerenityRest.lastResponse();

        Long currentId = response.jsonPath().getLong("id");
        Integer currentUserStatus = response.jsonPath().getInt("userStatus");

        return Objects.equals(currentId, expectedUser.getId())
                && Objects.equals(response.jsonPath().getString("username"), expectedUser.getUsername())
                && Objects.equals(response.jsonPath().getString("firstName"), expectedUser.getFirstName())
                && Objects.equals(response.jsonPath().getString("lastName"), expectedUser.getLastName())
                && Objects.equals(response.jsonPath().getString("email"), expectedUser.getEmail())
                && Objects.equals(response.jsonPath().getString("password"), expectedUser.getPassword())
                && Objects.equals(response.jsonPath().getString("phone"), expectedUser.getPhone())
                && Objects.equals(currentUserStatus, expectedUser.getUserStatus());
    }
}
