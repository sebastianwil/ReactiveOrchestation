package co.com.orchestation.model.user.gateways;

import co.com.orchestation.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> retrieveUsers();
}
