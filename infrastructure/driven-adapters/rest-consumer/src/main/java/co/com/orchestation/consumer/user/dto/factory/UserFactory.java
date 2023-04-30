package co.com.orchestation.consumer.user.dto.factory;

import co.com.orchestation.consumer.user.dto.response.UserDataDTO;
import co.com.orchestation.model.user.User;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class UserFactory {
    public Mono<User> execute(UserDataDTO user){
        var userApiDto = user.getData();
        return Mono.just(User.builder()
                        .userId(userApiDto.getUserId())
                        .firstname(userApiDto.getFirstname())
                        .lastname(userApiDto.getLastname())
                .build());
    }
}
