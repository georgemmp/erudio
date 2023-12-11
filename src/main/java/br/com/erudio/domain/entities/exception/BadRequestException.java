package br.com.erudio.domain.entities.exception;

import br.com.erudio.domain.interfaces.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException implements CustomizedException {

    private String message;
    private HttpStatus code;

    public BadRequestException(String message) {
        this.message = message;
        this.code = HttpStatus.BAD_REQUEST;
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
