package br.com.erudio.domain.entities.exception;

import br.com.erudio.domain.interfaces.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException implements CustomizedException {

    private String message;
    private HttpStatus code;

    public UnauthorizedException(String message) {
        this.message = message;
        this.code = HttpStatus.UNAUTHORIZED;
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
