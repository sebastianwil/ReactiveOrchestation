package co.com.orchestation.model.student;

import co.com.orchestation.model.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class Students {

    private List<Student> students;

}