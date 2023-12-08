package br.com.erudio.infrastrucutre.dtos.person;

import br.com.erudio.application.validations.person.PersonRequest;
import br.com.erudio.domain.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PersonRequest
public class PersonRequestDTO {

    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
}
