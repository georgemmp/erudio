package br.com.erudio.application.usecases.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPerson {

    @Autowired
    private PersonRepository repository;

    public List<Person> execute() {
        return this.repository.findAll();
    }
}
