package br.com.erudio.application.exceptions;

import br.com.erudio.domain.interfaces.ExceptionFactory;
import br.com.erudio.domain.types.ApiMessagesException;
import br.com.erudio.domain.types.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AbstractExceptionFactory {

    private ExceptionFactory factory;

    @Autowired
    private ApplicationContext applicationContext;

    public RuntimeException create(ExceptionType exceptionType, ApiMessagesException message, Object... args) {
        this.factory = exceptionType.getExceptionFactory(this.applicationContext);
        return factory.create(message, args);
    }
}
