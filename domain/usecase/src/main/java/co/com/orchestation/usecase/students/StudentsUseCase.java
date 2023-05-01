package co.com.orchestation.usecase.students;

import co.com.orchestation.model.student.Student;
import co.com.orchestation.model.student.gateways.StudentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StudentsUseCase {

    private final StudentRepository studentRepository;

    public Mono<Student> execute(){
        return studentRepository.retrieveStudents();
    }
}
