package co.com.orchestation.consumer.students;

import co.com.orchestation.consumer.commons.config.ConsumerProperty;
import co.com.orchestation.consumer.students.factory.StudentFactory;
import co.com.orchestation.consumer.commons.dto.Data;
import co.com.orchestation.consumer.users.factory.UserFactory;
import co.com.orchestation.model.persons.Persons;
import co.com.orchestation.model.persons.gateways.PersonsRepository;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import co.com.orchestation.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonsConsumer implements PersonsRepository {


    private final ConsumerProperty property;

    private final WebClient client;



    public Flux<Persons> retrievePersons() {

        Flux<Student> responseStudents = client
                .get()
                .uri(property.getHostApi2())
                .exchangeToFlux(studentsResponse->getStudentsResponse(studentsResponse));

        Flux<User> responseUsers = client
                .get()
                .uri(property.getHostApi1())
                .exchangeToFlux(clientResponse -> getUsersResponse(clientResponse));

        return Flux.zip(responseStudents.collectList(), responseUsers.collectList()).map(tuple -> new Persons(tuple.getT1(), tuple.getT2()))
                .onErrorMap(WebClientResponseException.NotFound.class, e ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found", e))
                .onErrorMap(WebClientResponseException.class, e ->
                        new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString(), e))
                .onErrorMap(e ->
                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e));
    }
    private Flux<Student> getStudentsResponse(ClientResponse studentResponse){

        return studentResponse.bodyToMono(Data.class)
                .flatMapMany(StudentFactory::execute);

    }
    private Flux<User> getUsersResponse(ClientResponse userResponse){

        return userResponse.bodyToMono(Data.class)
                .flatMapMany(UserFactory::execute);

    }
}