package br.com.erudio.application.exceptions;

import br.com.erudio.domain.interfaces.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException implements CustomizedException {

    private String message;
    private HttpStatus code;

    public ForbiddenException(String message) {
        this.message = message;
        this.code = HttpStatus.FORBIDDEN;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getCode() {
        return this.code;
    }
}
