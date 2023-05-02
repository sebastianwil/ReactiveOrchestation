package co.com.orchestation.model.user.gateways;

import co.com.orchestation.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Flux<User> retrieveUsers();

}
