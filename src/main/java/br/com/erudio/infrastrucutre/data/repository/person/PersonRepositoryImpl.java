package br.com.erudio.infrastrucutre.data.repository.person;

import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.domain.repositories.PersonRepository;
import br.com.erudio.infrastrucutre.data.datasource.person.PersonJpaRepository;
import br.com.erudio.infrastrucutre.data.models.person.PersonModel;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private PersonJpaRepository jpaRepository;

    @Autowired
    private PersonMapper mapper;

    @Override
    public Person findById(Long id) {
        Optional<PersonModel> model = this.jpaRepository.findById(id);
        return this.mapper.personToPersonModel(model.orElse(null));
    }

    @Override
    public Person save(Person person) {
        PersonModel personModel = this.mapper.personToPersonModel(person);
        PersonModel result = this.jpaRepository.save(personModel);
        return this.mapper.personToPersonModel(result);
    }

    @Override
    public void delete(Person person) {
        PersonModel personModel = this.mapper.personToPersonModel(person);
        this.jpaRepository.delete(personModel);
    }

    @Override
    public List<Person> findAll() {
        List<PersonModel> personModels = this.jpaRepository.findAll();
        return this.mapper.personModelListToPersonList(personModels);
    }
}
