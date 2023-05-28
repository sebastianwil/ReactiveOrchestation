package co.com.orchestation.model.persons;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.Students;
import co.com.orchestation.model.user.User;
import co.com.orchestation.model.user.Users;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Persons {
    List<Student> students;

    List<User> users;

}
