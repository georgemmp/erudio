package br.com.erudio.domain.interfaces;

import br.com.erudio.domain.types.ApiMessagesException;

public interface ExceptionFactory {

    RuntimeException create(ApiMessagesException exception, Object... args);
}
