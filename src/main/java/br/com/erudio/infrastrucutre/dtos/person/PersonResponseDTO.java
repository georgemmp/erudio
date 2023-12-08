package br.com.erudio.infrastrucutre.dtos.person;

import br.com.erudio.domain.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
}
