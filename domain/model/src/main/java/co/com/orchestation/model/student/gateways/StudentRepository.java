package co.com.orchestation.model.student.gateways;

import co.com.orchestation.model.student.Student;
import reactor.core.publisher.Mono;

public interface StudentRepository {
    Mono<Student> retrieveStudents();
}
