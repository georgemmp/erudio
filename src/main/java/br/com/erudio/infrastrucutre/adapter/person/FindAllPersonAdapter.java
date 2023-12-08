package br.com.erudio.infrastrucutre.adapter.person;

import br.com.erudio.application.usecases.person.FindAllPerson;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllPersonAdapter {

    @Autowired
    private FindAllPerson findAllPerson;

    @Autowired
    private PersonMapper mapper;

    public List<PersonResponseDTO> execute() {
        List<Person> person = this.findAllPerson.execute();
        return this.mapper.personListToPersonResponseList(person);
    }
}
