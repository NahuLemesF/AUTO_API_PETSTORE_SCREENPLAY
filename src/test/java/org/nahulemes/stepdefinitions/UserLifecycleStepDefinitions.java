package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.nahulemes.models.UserData;
import org.nahulemes.questions.ResponseFieldValue;
import org.nahulemes.questions.ResponseStatusCode;
import org.nahulemes.questions.UserResponseMatchesData;
import org.nahulemes.tasks.CreateUser;
import org.nahulemes.tasks.DeleteUser;
import org.nahulemes.tasks.GetUserByUsername;
import org.nahulemes.tasks.UpdateUser;
import org.nahulemes.util.TestData;

public class UserLifecycleStepDefinitions {

    private UserData initialUser;
    private UserData updatedUser;

    @Dado("que el administrador de API prepara un usuario para pruebas")
    public void queElAdministradorDeApiPreparaUnUsuarioParaPruebas() {
        initialUser = TestData.initialUser();
        updatedUser = TestData.updatedUserFrom(initialUser);

        OnStage.theActorCalled("Administrador API")
                .whoCan(CallAnApi.at(TestData.BASE_URL));
    }

    @Cuando("crea el usuario en Petstore")
    public void creaElUsuarioEnPetstore() {
        OnStage.theActorInTheSpotlight().attemptsTo(CreateUser.withData(initialUser));
    }

    @Entonces("la creacion del usuario es exitosa")
    public void laCreacionDelUsuarioEsExitosa() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(200),
                Ensure.that(ResponseFieldValue.fromBody("code")).isEqualTo("200"),
                Ensure.that(ResponseFieldValue.fromBody("type")).isEqualTo("unknown"),
                Ensure.that(ResponseFieldValue.fromBody("message")).isEqualTo(String.valueOf(initialUser.getId()))
        );
    }

    @Cuando("consulta el usuario creado por username")
    public void consultaElUsuarioCreadoPorUsername() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GetUserByUsername.called(initialUser.getUsername())
        );
    }

    @Entonces("la consulta inicial retorna los datos creados")
    public void laConsultaInicialRetornaLosDatosCreados() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(200),
                Ensure.that(ResponseFieldValue.fromBody("username")).isEqualTo(initialUser.getUsername()),
                Ensure.that(UserResponseMatchesData.forUser(initialUser)).isTrue()
        );
    }

    @Cuando("actualiza los datos del usuario")
    public void actualizaLosDatosDelUsuario() {
        OnStage.theActorInTheSpotlight().attemptsTo(UpdateUser.withData(updatedUser));
    }

    @Entonces("la actualizacion del usuario es exitosa")
    public void laActualizacionDelUsuarioEsExitosa() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(200),
                Ensure.that(ResponseFieldValue.fromBody("code")).isEqualTo("200"),
                Ensure.that(ResponseFieldValue.fromBody("type")).isEqualTo("unknown"),
                Ensure.that(ResponseFieldValue.fromBody("message")).isEqualTo(String.valueOf(updatedUser.getId()))
        );
    }

    @Cuando("consulta nuevamente el usuario actualizado")
    public void consultaNuevamenteElUsuarioActualizado() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GetUserByUsername.called(updatedUser.getUsername())
        );
    }

    @Entonces("la consulta posterior retorna los datos actualizados")
    public void laConsultaPosteriorRetornaLosDatosActualizados() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(200),
                Ensure.that(UserResponseMatchesData.forUser(updatedUser)).isTrue(),
                Ensure.that(ResponseFieldValue.fromBody("firstName")).isNotEqualTo(initialUser.getFirstName()),
                Ensure.that(ResponseFieldValue.fromBody("lastName")).isNotEqualTo(initialUser.getLastName()),
                Ensure.that(ResponseFieldValue.fromBody("email")).isNotEqualTo(initialUser.getEmail()),
                Ensure.that(ResponseFieldValue.fromBody("phone")).isNotEqualTo(initialUser.getPhone()),
                Ensure.that(ResponseFieldValue.fromBody("username")).isEqualTo(initialUser.getUsername()),
                Ensure.that(ResponseFieldValue.fromBody("id")).isEqualTo(String.valueOf(initialUser.getId()))
        );
    }

    @Cuando("elimina el usuario")
    public void eliminaElUsuario() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DeleteUser.called(updatedUser.getUsername())
        );
    }

    @Entonces("la eliminacion del usuario es exitosa")
    public void laEliminacionDelUsuarioEsExitosa() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(200),
                Ensure.that(ResponseFieldValue.fromBody("code")).isEqualTo("200"),
                Ensure.that(ResponseFieldValue.fromBody("type")).isEqualTo("unknown"),
                Ensure.that(ResponseFieldValue.fromBody("message")).isEqualTo(updatedUser.getUsername())
        );
    }

    @Cuando("consulta nuevamente el usuario eliminado")
    public void consultaNuevamenteElUsuarioEliminado() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GetUserByUsername.called(updatedUser.getUsername())
        );
    }

    @Entonces("la API informa que el usuario ya no existe")
    public void laApiInformaQueElUsuarioYaNoExiste() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ResponseStatusCode.was()).isEqualTo(404),
                Ensure.that(ResponseFieldValue.fromBody("code")).isEqualTo("1"),
                Ensure.that(ResponseFieldValue.fromBody("type")).isEqualTo("error"),
                Ensure.that(ResponseFieldValue.fromBody("message")).isEqualTo("User not found")
        );
    }
}
