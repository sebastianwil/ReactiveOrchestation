package co.com.orchestation.model.persons.gateways;

import co.com.orchestation.model.persons.Persons;
import co.com.orchestation.model.student.Student;
import reactor.core.publisher.Flux;

public interface PersonsRepository {
    Flux<Persons> retrievePersons();
}
