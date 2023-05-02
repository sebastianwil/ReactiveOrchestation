package co.com.orchestation.consumer.users;

import co.com.orchestation.consumer.users.dto.response.UserDataDTO;
import co.com.orchestation.consumer.users.factory.UserFactory;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.gateways.UserRepository;
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
public class UsersConsumer implements UserRepository {

    private final String uriTest1 = "/user";

    private final WebClient client;



    public Flux<User> retrieveUsers() {

        return client
            .get()
                .uri(uriTest1)
                .exchangeToFlux(clientResponse -> getClientResponse(clientResponse))
                .onErrorMap(WebClientResponseException.NotFound.class, e ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found", e))
                .onErrorMap(WebClientResponseException.class, e ->
                        new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString(), e))
                .onErrorMap(e ->
                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e));
    }
    private Flux<User> getClientResponse(ClientResponse clientResponse){

        return clientResponse.bodyToMono(UserDataDTO.class)
                .flatMapMany(UserFactory::execute);
    }
}