package br.com.erudio.application.exceptions;

import br.com.erudio.domain.types.ApiMessagesException;

public abstract class ExceptionFactory {

    protected abstract RuntimeException create(ApiMessagesException exception, Object... args);
}
