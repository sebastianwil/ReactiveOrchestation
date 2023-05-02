package co.com.orchestation.consumer.users;

import co.com.orchestation.consumer.users.dto.response.UserDTO;
import co.com.orchestation.consumer.users.dto.response.UserData;
import co.com.orchestation.consumer.users.dto.response.UserDataDTO;
import co.com.orchestation.consumer.users.factory.UserFactory;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.gateways.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsersConsumer implements UserRepository {

    private final String uriTest1 = "/user";

    private final WebClient client;



    public Flux<User> retrieveUsers() {

        return client
            .get()
                .uri(uriTest1)
                .exchangeToFlux(clientResponse -> getClientResponse(clientResponse));
    }
    private Flux<User> getClientResponse(ClientResponse clientResponse){

        return clientResponse.bodyToMono(UserDataDTO.class)
                .flatMapMany(UserFactory::execute);
    }
}