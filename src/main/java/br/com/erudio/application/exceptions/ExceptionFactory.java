package br.com.erudio.application.exceptions;

import br.com.erudio.domain.types.ApiMessagesException;

public abstract class ExceptionFactory {

    public RuntimeException create(ApiMessagesException exception, Object... args) {
        return this.setException(exception, args);
    }

    protected abstract RuntimeException setException(ApiMessagesException exception, Object... args);
}
