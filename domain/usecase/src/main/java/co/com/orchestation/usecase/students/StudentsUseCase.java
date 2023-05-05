package co.com.orchestation.usecase.students;

import co.com.orchestation.model.student.DataStudents;
import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.Students;
import co.com.orchestation.model.student.gateways.StudentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StudentsUseCase {

    private final StudentRepository studentRepository;

    public Mono<DataStudents> execute(){
        return studentRepository.retrieveStudents()
                .filter(student -> student.getRoom() != null && !student.getRoom().equalsIgnoreCase("sAla"))
                .collectList()
                .map(students -> DataStudents.builder()
                        .data(Students.builder()
                                .students(students)
                                .build())
                        .build());
    }
}
