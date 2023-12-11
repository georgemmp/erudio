package br.com.erudio.infrastrucutre.data.repository.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import br.com.erudio.infrastrucutre.data.datasource.person.PersonJpaRepository;
import br.com.erudio.infrastrucutre.data.datasource.person.PersonPagingAndSortJpaRepository;
import br.com.erudio.infrastrucutre.data.models.person.PersonModel;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private PersonJpaRepository jpaRepository;

    @Autowired
    private PersonPagingAndSortJpaRepository personPagingAndSortJpaRepository;

    @Autowired
    private PersonMapper mapper;

    @Override
    public Person findById(Long id) {
        Optional<PersonModel> model = this.jpaRepository.findById(id);
        return this.mapper.personModelToPerson(model.orElse(null));
    }

    @Override
    public Person save(Person person) {
        PersonModel personModel = this.mapper.personModelToPerson(person);
        PersonModel result = this.jpaRepository.save(personModel);
        return this.mapper.personModelToPerson(result);
    }

    @Override
    public void delete(Person person) {
        PersonModel personModel = this.mapper.personModelToPerson(person);
        this.jpaRepository.delete(personModel);
    }

    @Override
    public List<Person> findAll() {
        List<PersonModel> personModels = this.jpaRepository.findAll();
        return this.mapper.personModelListToPersonList(personModels);
    }

    @Override
    public Page<Person> findAllPage(Pageable pageable) {
        Page<PersonModel> pagePerson = this.personPagingAndSortJpaRepository.findAll(pageable);
        return pagePerson.map(personModel -> this.mapper.personModelToPerson(personModel));
    }
}
