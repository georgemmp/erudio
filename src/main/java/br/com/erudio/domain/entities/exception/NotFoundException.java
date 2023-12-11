package br.com.erudio.domain.entities.exception;

import br.com.erudio.domain.interfaces.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements CustomizedException {

    private String message;
    private HttpStatus code;

    public NotFoundException(String message) {
        this.message = message;
        this.code = HttpStatus.NOT_FOUND;
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
