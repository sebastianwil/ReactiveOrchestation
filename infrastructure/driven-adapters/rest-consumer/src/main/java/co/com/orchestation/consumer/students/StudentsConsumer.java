package co.com.orchestation.consumer.students;

import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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