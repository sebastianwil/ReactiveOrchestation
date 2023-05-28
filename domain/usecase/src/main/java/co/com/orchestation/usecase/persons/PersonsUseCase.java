package co.com.orchestation.usecase.persons;

import co.com.orchestation.model.persons.DataPersons;
import co.com.orchestation.model.persons.Persons;
import co.com.orchestation.model.persons.gateways.PersonsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonsUseCase {

    private final PersonsRepository personsRepository;

    public Mono<DataPersons> execute() {

        return personsRepository.retrievePersons()
                .map(persons -> DataPersons.builder()
                        .data(persons).build())
                .single();

    }
}
