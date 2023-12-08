package br.com.erudio.application.usecases.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePerson {

    @Autowired
    private PersonRepository repository;

    public Person execute(Person person) {
        return this.repository.save(person);
    }
}
