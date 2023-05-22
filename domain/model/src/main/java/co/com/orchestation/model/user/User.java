package co.com.orchestation.model.user;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class User {

    private int userId;

    private String firstName;

    private String lastName;

}
