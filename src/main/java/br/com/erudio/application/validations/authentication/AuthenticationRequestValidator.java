package br.com.erudio.application.validations.authentication;

import br.com.erudio.application.validations.person.PersonRequest;
import br.com.erudio.domain.entities.validation.FieldMessage;
import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;
import br.com.erudio.infrastrucutre.dtos.person.PersonRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthenticationRequestValidator implements ConstraintValidator<AuthenticationRequest, AuthenticationDTO> {

    @Override
    public void initialize(AuthenticationRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AuthenticationDTO authenticationDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (Objects.isNull(authenticationDTO.getUserName()) || Strings.isBlank(authenticationDTO.getUserName())) {
            fieldMessages.add(new FieldMessage("userName", "{user.username}"));
        }

        if (Objects.isNull(authenticationDTO.getPassword()) || Strings.isBlank(authenticationDTO.getPassword())) {
            fieldMessages.add(new FieldMessage("password", "{user.password}"));
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
