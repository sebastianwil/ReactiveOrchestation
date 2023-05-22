package co.com.orchestation.model.student;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student {

    private int id;

    private String firstName;

    private String lastName;

    private String room;

    private Boolean isUser;

}
