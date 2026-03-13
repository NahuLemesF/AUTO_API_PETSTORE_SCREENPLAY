package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.nahulemes.models.UserData;
import org.nahulemes.util.RequestSpecs;

public class UpdateUser implements Task {

    private final UserData userData;

    public UpdateUser(UserData userData) {
        this.userData = userData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/user/{username}")
                .with(request -> RequestSpecs.json(request)
                                .pathParam("username", userData.getUsername())
                                .body(userData))
        );
    }

    public static UpdateUser withData(UserData userData) {
        return Tasks.instrumented(UpdateUser.class, userData);
    }
}
