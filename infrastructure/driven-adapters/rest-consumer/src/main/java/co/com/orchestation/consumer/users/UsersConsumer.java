package co.com.orchestation.consumer.users;

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
public class UsersConsumer implements UserRepository {

    private final String uriTest1 = "/user";

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
                }).onErrorResume(e -> {
                    if (e instanceof WebClientResponseException.NotFound) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
                    } else if (e instanceof WebClientResponseException wcre) {
                        return Mono.error(new ResponseStatusException(wcre.getStatusCode(), wcre.getResponseBodyAsString()));
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                    }
                });
    }
}