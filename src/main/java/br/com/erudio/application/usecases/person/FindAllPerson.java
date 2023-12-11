package br.com.erudio.application.usecases.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPerson {

    @Autowired
    private PersonRepository repository;

    public Page<Person> execute(Pageable pageable) {
        return this.repository.findAllPage(pageable);
    }
}
