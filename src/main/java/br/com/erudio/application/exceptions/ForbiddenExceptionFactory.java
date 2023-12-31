package br.com.erudio.application.exceptions;

import br.com.erudio.domain.entities.exception.ForbiddenException;
import br.com.erudio.domain.interfaces.ExceptionFactory;
import br.com.erudio.domain.types.ApiMessagesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ForbiddenExceptionFactory implements ExceptionFactory {

    @Autowired
    private MessageSource messageSource;

    @Override
    public RuntimeException create(ApiMessagesException exception, Object... args) {
        String message = this.messageSource.getMessage(exception.getMessage(), args, Locale.ENGLISH);
        return exception.customizedException(new ForbiddenException(message));
    }
}
