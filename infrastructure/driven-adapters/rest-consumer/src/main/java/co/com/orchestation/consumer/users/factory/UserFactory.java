package co.com.orchestation.consumer.users.factory;

import co.com.orchestation.consumer.users.dto.response.UserDataDTO;
import co.com.orchestation.model.user.User;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserFactory {
    public Flux<User> execute(UserDataDTO user){
        List<User> users = user.getData()
                .stream().map( userDto ->
                        User.builder()
                                .userId(userDto.getUserId())
                                .lastname(userDto.getLastname())
                                .firstname(userDto.getFirstname())
                                .build()
                ).collect(Collectors.toList());

        return Mono.just(users).flatMapMany(Flux::fromIterable);
    }
}
