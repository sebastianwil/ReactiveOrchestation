package co.com.orchestation.api.users.common.users;

import co.com.orchestation.model.student.DataStudents;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.Students;
import co.com.orchestation.model.user.DataUsers;
import co.com.orchestation.model.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
public class DataStudentsBuilder {

    private Students data;

    public DataStudentsBuilder(){

        this.data =  Students.builder().students(new ArrayList<>()).build();
    }

    public DataStudents build(){
        data.getStudents().add(Student.builder().id(54321).firstName("SebasTest").lastName("WilchesTest").room("CasaTest").isUser(false).build());
        return DataStudents.builder().data(data).build();
    }
}
