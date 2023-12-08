package br.com.erudio.core.exceptions;

import br.com.erudio.domain.entities.validation.StandardError;
import br.com.erudio.domain.interfaces.CustomizedException;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class StandardErrorFactory {

    private StandardErrorFactory() {
    }

    public static StandardError create(CustomizedException exception, HttpServletRequest request) {
        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(exception.getCode().value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
