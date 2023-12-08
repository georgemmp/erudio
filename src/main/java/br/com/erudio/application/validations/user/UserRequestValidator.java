package br.com.erudio.application.validations.user;

import br.com.erudio.domain.entities.validation.FieldMessage;
import br.com.erudio.infrastrucutre.dtos.user.UserRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRequestValidator implements ConstraintValidator<UserRequest, UserRequestDTO> {

    @Override
    public void initialize(UserRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequestDTO userRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (Objects.isNull(userRequestDTO.getUserName()) || Strings.isBlank(userRequestDTO.getUserName())) {
            fieldMessages.add(new FieldMessage("userName", "{user.username}"));
        }

        if (Objects.isNull(userRequestDTO.getPassword()) || Strings.isBlank(userRequestDTO.getPassword())) {
            fieldMessages.add(new FieldMessage("password", "{user.password}"));
        }

        if (Objects.isNull(userRequestDTO.getFullName()) || Strings.isBlank(userRequestDTO.getFullName())) {
            fieldMessages.add(new FieldMessage("fullName", "{user.full-name}"));
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
