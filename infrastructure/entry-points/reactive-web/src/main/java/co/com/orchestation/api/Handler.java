package co.com.orchestation.api;


import co.com.orchestation.usecase.students.StudentsUseCase;
import co.com.orchestation.usecase.users.UsersUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {
private final UsersUseCase usersUseCase;
private final StudentsUseCase studentsUseCase;

    public Handler(UsersUseCase usersUseCase, StudentsUseCase studentsUseCase){
        this.usersUseCase =usersUseCase;
        this.studentsUseCase=studentsUseCase;
    }
    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {

        return Mono.just(usersUseCase.execute()).flatMap(user -> ServerResponse.ok().body(user, user.getClass()));
    }

    public Mono<ServerResponse> getStudents(ServerRequest serverRequest) {
        return Mono.just(studentsUseCase.execute()).flatMap(students -> ServerResponse.ok().body(students, students.getClass()));
    }
}
