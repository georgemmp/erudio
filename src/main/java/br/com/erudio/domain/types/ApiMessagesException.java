package br.com.erudio.domain.types;

import br.com.erudio.domain.interfaces.CustomizedException;

import java.util.function.BiFunction;

public enum ApiMessagesException {

    PERSON_NOT_FOUND("person.not-found"),
    USER_NOT_FOUND("user.not-found"),
    INVALID_CREDENTIALS("jwt.invalid-credentials"),
    INVALID_TOKEN("jwt.invalid-token");

    private String message;

    ApiMessagesException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @SuppressWarnings("unchecked")
    private <T extends RuntimeException> T exception(BiFunction<String, T, T> error, CustomizedException exception) {
        return error.apply(String.format(message), (T) exception);
    }

    public RuntimeException customizedException(CustomizedException error) {
        return exception((msg, exception) -> exception, error);
    }
}
