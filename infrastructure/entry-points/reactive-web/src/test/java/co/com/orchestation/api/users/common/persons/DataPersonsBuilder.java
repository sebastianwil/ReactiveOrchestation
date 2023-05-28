package co.com.orchestation.api.users.common.persons;

import co.com.orchestation.model.persons.DataPersons;
import co.com.orchestation.model.persons.Persons;
import co.com.orchestation.model.student.DataStudents;
import co.com.orchestation.model.user.DataUsers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataPersonsBuilder  {

    private Persons data;

    public DataPersons build(DataUsers users, DataStudents students){

        return DataPersons.builder().data(data.builder()
                .students(students.getData().getStudents())
                .users(users.getData().getUsers()).build())
                .build();
    }
}
