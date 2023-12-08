package br.com.erudio.infrastrucutre.data.models.person;

import br.com.erudio.domain.types.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "person")
public class PersonModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 9)
    private Gender gender;
}
