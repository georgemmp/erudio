package br.com.erudio.infrastrucutre.adapter.person;

import br.com.erudio.application.usecases.person.FindPersonById;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.domain.entities.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPersonByIdAdapter {

    @Autowired
    private FindPersonById findPersonById;

    @Autowired
    private PersonMapper mapper;

    public PersonResponseDTO execute(Long id) {
        Person person = this.findPersonById.execute(id);
        return this.mapper.personToPersonResponseDto(person);
    }
}
