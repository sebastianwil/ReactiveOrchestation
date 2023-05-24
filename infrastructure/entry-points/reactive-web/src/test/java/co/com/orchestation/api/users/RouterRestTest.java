package co.com.orchestation.api.users;

import co.com.orchestation.api.Handler;
import co.com.orchestation.api.RouterRest;
import co.com.orchestation.api.users.common.users.DataStudentsBuilder;
import co.com.orchestation.api.users.common.data.UserData;
import co.com.orchestation.api.users.common.users.DataUsersBuilder;
import co.com.orchestation.consumer.commons.config.ConsumerProperty;
import co.com.orchestation.usecase.students.StudentsUseCase;
import co.com.orchestation.usecase.users.UsersUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
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
    void shouldDisplayUsersArrayAfterGetUsers() {

        Mockito.when(usersUseCase.execute())
                .thenReturn(Mono.just(new DataUsersBuilder().build()));

        statusAssertions(userData.USERS_PATH)
                .isOk()
                .expectBody()
                .jsonPath("$.data.users").isArray()
                .jsonPath("$.data.users[0].lastName").isEqualTo("Testing")
                .jsonPath("$.data.users[0].firstName").isEqualTo("Probando")
                .jsonPath("$.data.users[0].userId").isEqualTo(12345);
    }
    @Test
    void shouldDisplayStudentsArrayAfterGetUsers() {

        Mockito.when(studentsUseCase.execute())
                .thenReturn(Mono.just(new DataStudentsBuilder().build()));

        statusAssertions(userData.STUDENTS_PATH)
                .isOk()
                .expectBody()
                .jsonPath("$.data.students").isArray()
                .jsonPath("$.data.students[0].lastName").isEqualTo("WilchesTest")
                .jsonPath("$.data.students[0].firstName").isEqualTo("SebasTest")
                .jsonPath("$.data.students[0].id").isEqualTo(54321)
                .jsonPath("$.data.students[0].room").isEqualTo("CasaTest")
                .jsonPath("$.data.students[0].isUser").isEqualTo(false);
    }

    private <T> StatusAssertions statusAssertions(String path) {

        return webTestClient.get().uri(path)
                .exchange()
                .expectStatus();

    }
}
