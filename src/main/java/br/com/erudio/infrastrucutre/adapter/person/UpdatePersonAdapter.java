package br.com.erudio.infrastrucutre.adapter.person;

import br.com.erudio.application.usecases.person.SavePerson;
import br.com.erudio.application.usecases.person.UpdatePerson;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonUpdateDTO;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonAdapter {

    @Autowired
    private UpdatePerson updatePerson;

    @Autowired
    private PersonMapper mapper;

    public PersonResponseDTO execute(PersonUpdateDTO dto, Long id) {
        Person person = this.mapper.personUpdateDTOToPerson(dto);
        Person result = this.updatePerson.execute(person, id);
        return this.mapper.personToPersonResponseDto(result);
    }
}
