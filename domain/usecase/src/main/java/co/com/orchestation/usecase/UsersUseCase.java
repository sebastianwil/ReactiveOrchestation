package co.com.orchestation.usecase;

import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UsersUseCase {
    private final UserRepository userRepository;
    public Mono<User> execute(){
        return userRepository.retrieveUsers();
    }
}
