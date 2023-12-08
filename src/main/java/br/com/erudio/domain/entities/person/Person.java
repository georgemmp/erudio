package br.com.erudio.domain.entities.person;

import br.com.erudio.domain.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;

    public Person copy(Person person) {
        return Person.builder()
                .id(person.getId() != null ? person.getId() : this.id)
                .address(person.getAddress() != null ? person.getAddress() : this.address)
                .firstName(person.getFirstName() != null ? person.getFirstName() :  this.firstName)
                .lastName(person.getLastName() != null ? person.getLastName() : this.lastName)
                .gender(person.getGender() != null ? person.getGender() : this.gender)
                .build();
    }
}
