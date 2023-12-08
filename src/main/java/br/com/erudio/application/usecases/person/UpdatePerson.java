package br.com.erudio.application.usecases.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePerson {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private FindPersonById findPersonById;

    public Person execute(Person person, Long id) {
        Person result = this.findPersonById.execute(id);
        Person updatedPerson = result.copy(person);

        return this.repository.save(updatedPerson);
    }
}
