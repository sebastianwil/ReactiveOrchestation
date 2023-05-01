package co.com.orchestation.consumer.user;

import co.com.orchestation.consumer.config.RestConsumerConfig;
import co.com.orchestation.consumer.user.dto.request.UserDataDTO;
import co.com.orchestation.consumer.user.dto.response.UserDTO;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.gateways.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements UserRepository, StudentRepository {

    private final String uriTest1 = "/users";

    private final String uriTest2 = "http://localhost:3001/student";

    private final WebClient client;



    public Mono<User> retrieveUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        return client
            .get()
                .uri(uriTest1)
            .retrieve()
            .bodyToMono(String.class)
                .flatMap(json -> {
                    try {
                        User user = objectMapper.readValue(json, User.class);
                        return Mono.just(user);
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException("Error parsing response: " + e.getMessage()));
                    }
                });
    }

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

    public Mono<UserDTO> testPost() {

        UserDataDTO request = UserDataDTO.builder()
            .val1("exampleval1")
            .val2("exampleval2")
            .build();

        return client
            .post()
            .body(Mono.just(request), UserDataDTO.class)
            .retrieve()
            .bodyToMono(UserDTO.class);
    }
}