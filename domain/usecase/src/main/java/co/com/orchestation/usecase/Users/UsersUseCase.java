package co.com.orchestation.usecase.Users;

import co.com.orchestation.model.user.Users;
import co.com.orchestation.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class UsersUseCase {
    private final UserRepository userRepository;
    public Mono<Users> execute(){
        return userRepository.retrieveUsers()
                .collectList()
                .map(Users::new);
    }
}
