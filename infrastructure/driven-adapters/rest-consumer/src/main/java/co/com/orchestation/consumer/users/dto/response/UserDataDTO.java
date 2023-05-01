package co.com.orchestation.consumer.users.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDataDTO {
    UserDTO data;
}
