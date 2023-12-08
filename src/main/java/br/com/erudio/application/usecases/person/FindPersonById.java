package br.com.erudio.application.usecases.person;

import br.com.erudio.application.exceptions.AbstractExceptionFactory;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import br.com.erudio.domain.types.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.erudio.domain.types.ApiMessagesException.PERSON_NOT_FOUND;

@Service
public class FindPersonById {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private AbstractExceptionFactory exceptionFactory;

    public Person execute(Long id) {
        Person person = this.repository.findById(id);

        if (Objects.isNull(person)) {
            throw this.exceptionFactory.create(ExceptionType.NOT_FOUND, PERSON_NOT_FOUND);
        }

        return person;
    }
}
