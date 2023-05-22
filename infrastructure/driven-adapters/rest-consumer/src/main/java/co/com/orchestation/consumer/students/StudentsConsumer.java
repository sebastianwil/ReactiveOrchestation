package co.com.orchestation.consumer.students;

import co.com.orchestation.consumer.commons.config.ConsumerProperty;
import co.com.orchestation.consumer.students.factory.StudentFactory;
import co.com.orchestation.consumer.commons.dto.Data;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StudentsConsumer implements StudentRepository {


    private final ConsumerProperty property;

    private final WebClient client;



    public Flux<Student> retrieveStudents() {

        return client
                .get()
                .uri(property.getHostApi2())
                .exchangeToFlux(studentsResponse->getStudentsResponse(studentsResponse))
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
}