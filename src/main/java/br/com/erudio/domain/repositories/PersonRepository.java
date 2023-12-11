package br.com.erudio.domain.repositories;

import br.com.erudio.domain.entities.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonRepository {

    Person findById(Long id);
    Person save(Person person);
    void delete(Person person);

    List<Person> findAll();
    Page<Person> findAllPage(Pageable pageable);
}
