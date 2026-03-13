package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.nahulemes.models.UserData;
import org.nahulemes.util.RequestSpecs;

public class CreateUser implements Task {

    private final UserData userData;

    public CreateUser(UserData userData) {
        this.userData = userData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/user")
                .with(request -> RequestSpecs.json(request)
                                .body(userData))
        );
    }

    public static CreateUser withData(UserData userData) {
        return Tasks.instrumented(CreateUser.class, userData);
    }
}
