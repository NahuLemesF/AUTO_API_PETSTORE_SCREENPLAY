package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.nahulemes.util.RequestSpecs;

public class GetUserByUsername implements Task {

    private final String username;

    public GetUserByUsername(String username) {
        this.username = username;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/user/{username}")
                .with(request -> RequestSpecs.json(request)
                    .pathParam("username", username))
        );
    }

    public static GetUserByUsername called(String username) {
        return Tasks.instrumented(GetUserByUsername.class, username);
    }
}
