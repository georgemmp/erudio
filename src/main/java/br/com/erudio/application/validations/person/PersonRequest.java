package br.com.erudio.application.validations.person;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PersonRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonRequest {

    String message() default "Person validation error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
