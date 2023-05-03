package co.com.orchestation.usecase.users;

import co.com.orchestation.model.user.DataUsers;
import co.com.orchestation.model.user.Users;
import co.com.orchestation.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class UsersUseCase {
    private final UserRepository userRepository;

    public Mono<DataUsers> execute(){
        return userRepository.retrieveUsers()
                .collectList()
                .map(users -> DataUsers.builder()
                        .data(Users.builder()
                                .users(users)
                                .build())
                        .build());
    }
}
