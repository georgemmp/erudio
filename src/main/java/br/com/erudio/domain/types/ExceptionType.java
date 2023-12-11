package br.com.erudio.domain.types;

import br.com.erudio.application.exceptions.*;
import br.com.erudio.domain.interfaces.ExceptionFactory;
import org.springframework.context.ApplicationContext;

public enum ExceptionType {

    NOT_FOUND(NotFoundExceptionFactory.class),
    BAD_REQUEST(BadRequestExceptionFactory.class),
    FORBIDDEN(ForbiddenExceptionFactory.class),
    UNAUTHORIZED(UnauthorizedExceptionFactory.class);

    private final Class<ExceptionFactory> exceptionFactoryClass;

    @SuppressWarnings(value = "unchecked")
    <T extends ExceptionFactory> ExceptionType(Class<T> exceptionFactoryClass) {
        this.exceptionFactoryClass = (Class<ExceptionFactory>) exceptionFactoryClass;
    }

    public ExceptionFactory getExceptionFactory(ApplicationContext applicationContext) {
        return applicationContext.getBean(this.exceptionFactoryClass);
    }
}
