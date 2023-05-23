package co.com.orchestation.api.users.common.users;

import co.com.orchestation.api.users.common.users.UserBuilder;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class UsersBuilder {

    private List<User> users;

    public  UsersBuilder (){

        this.users = new ArrayList<>();

    }

    public Users build(){

        users.add(new UserBuilder().build());

        return Users.builder().users(users).build();

    }
}
