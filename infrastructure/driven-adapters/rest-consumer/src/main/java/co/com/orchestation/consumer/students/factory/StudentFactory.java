package co.com.orchestation.consumer.students.factory;

import co.com.orchestation.consumer.commons.dto.Data;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.user.User;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StudentFactory {
    public Flux<Student> execute(Data data){
        List<Student> students = data.getData().getStudents()
                .stream().map( studentDto ->
                        Student.builder()
                                .id(studentDto.getId())
                                .lastName(studentDto.getLastName())
                                .firstName(studentDto.getFirstName())
                                .room(studentDto.getRoom())
                                .isUser(studentDto.getIsUser())
                                .build()
                ).collect(Collectors.toList());

        return Mono.just(students).flatMapMany(Flux::fromIterable);
    }
}
