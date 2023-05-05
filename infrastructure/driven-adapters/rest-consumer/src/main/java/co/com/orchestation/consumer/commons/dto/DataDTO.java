package co.com.orchestation.consumer.commons.dto;

import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {

    List<Student> students;
    List<User> users;

}
