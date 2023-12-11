package br.com.erudio.infrastrucutre.adapter.person;

import br.com.erudio.application.usecases.person.FindAllPerson;
import br.com.erudio.domain.entities.person.Person;
import br.com.erudio.infrastrucutre.dtos.person.PersonResponseDTO;
import br.com.erudio.infrastrucutre.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllPersonAdapter {

    @Autowired
    private FindAllPerson findAllPerson;

    @Autowired
    private PersonMapper mapper;

    public Page<PersonResponseDTO> execute(Pageable pageable) {
        Page<Person> page = this.findAllPerson.execute(pageable);
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), people.size());
//        List<Person> pageContent = people.subList(start, end);
//        Page<Person> page = new PageImpl<>(pageContent, pageable, people.size());
        return page.map(person -> this.mapper.personToPersonResponseDto(person));
    }
}
