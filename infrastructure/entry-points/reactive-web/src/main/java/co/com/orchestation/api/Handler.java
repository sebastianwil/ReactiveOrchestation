package co.com.orchestation.api;


import co.com.orchestation.usecase.persons.PersonsUseCase;
import co.com.orchestation.usecase.students.StudentsUseCase;
import co.com.orchestation.usecase.users.UsersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {
private final UsersUseCase usersUseCase;
private final StudentsUseCase studentsUseCase;
private final PersonsUseCase personsUseCase;

    public Handler(UsersUseCase usersUseCase, StudentsUseCase studentsUseCase, PersonsUseCase personsUseCase){
        this.usersUseCase =usersUseCase;
        this.studentsUseCase=studentsUseCase;
        this.personsUseCase = personsUseCase;
    }
    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {

        return Mono.just(usersUseCase.execute()).flatMap(user -> ServerResponse.ok().body(user, user.getClass()));

    }

    public Mono<ServerResponse> getStudents(ServerRequest serverRequest) {

        return Mono.just(studentsUseCase.execute()).flatMap(students -> ServerResponse.ok().body(students, students.getClass()));

    }

    public Mono<ServerResponse> getPersons(ServerRequest serverRequest) {

        return Mono.just(personsUseCase.execute()).flatMap(persons -> ServerResponse.ok().body(persons, persons.getClass()));
    }

}
