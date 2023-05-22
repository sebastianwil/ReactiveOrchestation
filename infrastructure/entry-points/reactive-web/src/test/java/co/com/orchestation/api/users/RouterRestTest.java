package co.com.orchestation.api.users;

import co.com.orchestation.api.Handler;
import co.com.orchestation.api.RouterRest;
import co.com.orchestation.api.users.common.DataUsersBuilder;
import co.com.orchestation.api.users.common.data.UserData;
import co.com.orchestation.consumer.commons.config.ConsumerProperty;
import co.com.orchestation.model.user.DataUsers;
import co.com.orchestation.model.user.User;
import co.com.orchestation.usecase.students.StudentsUseCase;
import co.com.orchestation.usecase.users.UsersUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@ContextConfiguration(classes = {RouterRest.class, Handler.class, ConsumerProperty.class, UserData.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    ConsumerProperty property;
    @Autowired
    private UserData userData;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UsersUseCase usersUseCase;

    @MockBean
    private StudentsUseCase studentsUseCase;

    @Test
    void shouldDisplayUsersArraytGetUsers() {

        Mockito.when(usersUseCase.execute())
                .thenReturn(Mono.just(new DataUsersBuilder().build()));

        statusAssertions(userData.USER_PATH)
                .isOk()
                .expectBody()
                .jsonPath("$.data.users").isArray()
                .jsonPath("$.data.users[0].lastName").isEqualTo("Testing")
                .jsonPath("$.data.users[0].firstName").isEqualTo("Probando")
                .jsonPath("$.data.users[0].userId").isEqualTo(12345);
    }

    private <T> StatusAssertions statusAssertions(String path) {

        return webTestClient.get().uri(path)
                .exchange()
                .expectStatus();

    }
}
