package co.com.orchestation.api;

import co.com.orchestation.model.user.User;
import co.com.orchestation.usecase.UsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.w3c.dom.UserDataHandler;
import reactor.core.publisher.Mono;

@Component
public class Handler {
private final UsersUseCase usersUseCase;

    public Handler(UsersUseCase usersUseCase){
        this.usersUseCase =usersUseCase;
    }
    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {

        return Mono.just(usersUseCase.execute()).flatMap(user -> ServerResponse.ok().body(user, user.getClass()));
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }
}
