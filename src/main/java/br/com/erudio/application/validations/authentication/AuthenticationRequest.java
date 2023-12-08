package br.com.erudio.application.validations.authentication;

import br.com.erudio.application.validations.person.PersonRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AuthenticationRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationRequest {

    String message() default "Authentication validation error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
