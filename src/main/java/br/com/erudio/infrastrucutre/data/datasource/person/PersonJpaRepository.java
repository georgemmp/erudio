package br.com.erudio.infrastrucutre.data.datasource.person;

import br.com.erudio.infrastrucutre.data.models.person.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonModel, Long> {
}
