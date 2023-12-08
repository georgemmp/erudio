package br.com.erudio.mocks;

import br.com.erudio.domain.types.Gender;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;

import static br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO.*;

public class PersonDTOFactory {

    public static PersonRequestDTOBuilder createPersonRequestDTO() {
        return PersonRequestDTO.builder()
                .firstName("Maria")
                .address("Rua do Test")
                .gender(Gender.FEMALE)
                .lastName("Test");
    }

    public static PersonResponseDTO.PersonResponseDTOBuilder createPersonResponseDTO() {
        return PersonResponseDTO.builder()
                .id(1L)
                .firstName("Maria")
                .address("Rua do Test")
                .gender(Gender.FEMALE)
                .lastName("Test");
    }
}
