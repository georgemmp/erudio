package br.com.erudio.infrastrucutre.adapter.person;

import br.com.erudio.application.usecases.person.SavePerson;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.domain.entities.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePersonAdapter {

    @Autowired
    private SavePerson savePerson;

    @Autowired
    private PersonMapper mapper;

    public PersonResponseDTO execute(PersonRequestDTO dto) {
        Person person = this.mapper.personRequestDTOToPerson(dto);
        Person result = this.savePerson.execute(person);
        return this.mapper.personToPersonResponseDto(result);
    }
}
