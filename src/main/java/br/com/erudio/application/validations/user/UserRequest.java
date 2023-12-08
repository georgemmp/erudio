package br.com.erudio.application.validations.user;

import br.com.erudio.application.validations.person.PersonRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRequest {

    String message() default "User validation error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
