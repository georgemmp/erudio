package br.com.erudio.infrastrucutre.mapper;

import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.infrastrucutre.data.models.person.PersonModel;
import br.com.erudio.infrastrucutre.dtos.person.PersonUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person personModelToPerson(PersonModel personModel);

    PersonModel personModelToPerson(Person person);

    PersonResponseDTO personToPersonResponseDto(Person person);

    @Mapping(target = "id", ignore = true)
    Person personRequestDTOToPerson(PersonRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    Person personUpdateDTOToPerson(PersonUpdateDTO dto);

    List<Person> personModelListToPersonList(List<PersonModel> personModels);

    List<PersonResponseDTO> personListToPersonResponseList(List<Person> person);
}
