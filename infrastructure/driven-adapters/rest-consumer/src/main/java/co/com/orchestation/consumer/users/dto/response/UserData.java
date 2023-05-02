package co.com.orchestation.consumer.users.dto.response;

import co.com.orchestation.model.user.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserData {
    List<User> data;
}