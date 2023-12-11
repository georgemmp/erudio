package br.com.erudio.infrastrucutre.data.datasource.person;

import br.com.erudio.infrastrucutre.data.models.person.PersonModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonPagingAndSortJpaRepository extends PagingAndSortingRepository<PersonModel, Long> {
}
