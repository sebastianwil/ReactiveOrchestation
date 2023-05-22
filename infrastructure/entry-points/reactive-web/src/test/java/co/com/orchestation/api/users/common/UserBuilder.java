package co.com.orchestation.api.users.common;

import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UserBuilder {

    private Users data;
    private int userId;

    private String firstName;

    private String lastName;

    public  UserBuilder (){

        this.firstName = "Probando";

        this.userId = 12345;

        this.lastName = "Testing";

    }
    public User build(){

        return User.builder().firstName(firstName).userId(userId).lastName(lastName).build();
    }
}
