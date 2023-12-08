package br.com.erudio.application.validations.person;

import br.com.erudio.domain.entities.validation.FieldMessage;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonRequestValidator implements ConstraintValidator<PersonRequest, PersonRequestDTO> {

    @Override
    public void initialize(PersonRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonRequestDTO personRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (Objects.isNull(personRequestDTO.getFirstName()) || Strings.isBlank(personRequestDTO.getFirstName())) {
            fieldMessages.add(new FieldMessage("firstName", "{person.first-name-required}"));
        }

        if (Objects.isNull(personRequestDTO.getLastName()) || Strings.isBlank(personRequestDTO.getLastName())) {
            fieldMessages.add(new FieldMessage("lastName", "{person.last-name-required}"));
        }

        if (Objects.isNull(personRequestDTO.getAddress()) || Strings.isBlank(personRequestDTO.getAddress())) {
            fieldMessages.add(new FieldMessage("address", "{person.address-required}"));
        }

        if (Objects.isNull(personRequestDTO.getGender())) {
            fieldMessages.add(new FieldMessage("gender", "{person.gender-required}"));
        }

        for (FieldMessage fieldMessage: fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldMessage())
                    .addConstraintViolation();
        }

        return fieldMessages.isEmpty();
    }
}
