package co.com.orchestation.api.users.common;

import co.com.orchestation.model.user.DataUsers;
import co.com.orchestation.model.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class DataUsersBuilder {

    private Users data;

    public DataUsersBuilder(){

        this.data = new UsersBuilder().build();
    }

    public DataUsers build(){

        return DataUsers.builder().data(data).build();
    }
}
