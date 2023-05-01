package co.com.orchestation.consumer.students;

import co.com.orchestation.consumer.user.dto.request.UserDataDTO;
import co.com.orchestation.consumer.user.dto.response.UserDTO;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.gateways.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentsConsumer implements StudentRepository {



    private final String uriTest2 = "http://localhost:3001/student";

    private final WebClient client;



    public Mono<Student> retrieveStudents() {
        ObjectMapper objectMapper = new ObjectMapper();

        return client
                .get()
                .uri(uriTest2)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(json -> {
                    try {
                        Student student = objectMapper.readValue(json, Student.class);
                        return Mono.just(student);
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException("Error parsing response: " + e.getMessage()));
                    }
                });
    }
}