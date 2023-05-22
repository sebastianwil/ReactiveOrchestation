package co.com.orchestation.model.student.gateways;

import co.com.orchestation.model.student.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {
    Flux<Student> retrieveStudents();
}
