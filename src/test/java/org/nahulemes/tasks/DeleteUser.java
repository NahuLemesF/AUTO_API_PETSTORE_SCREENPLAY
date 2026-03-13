package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import org.nahulemes.util.RequestSpecs;

public class DeleteUser implements Task {

    private final String username;

    public DeleteUser(String username) {
        this.username = username;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/{username}")
                .with(request -> RequestSpecs.json(request)
                    .pathParam("username", username))
        );
    }

    public static DeleteUser called(String username) {
        return Tasks.instrumented(DeleteUser.class, username);
    }
}
