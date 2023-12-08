package br.com.erudio.core.exceptions;

import br.com.erudio.application.exceptions.BadRequestException;
import br.com.erudio.application.exceptions.ForbiddenException;
import br.com.erudio.application.exceptions.NotFoundException;
import br.com.erudio.application.exceptions.UnauthorizedException;
import br.com.erudio.domain.entities.validation.StandardError;
import br.com.erudio.domain.entities.validation.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException exception, HttpServletRequest request) {
        StandardError error = StandardErrorFactory.create(exception, request);
        return ResponseEntity.status(exception.getCode()).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest(BadRequestException exception, HttpServletRequest request) {
        StandardError error = StandardErrorFactory.create(exception, request);
        return ResponseEntity.status(exception.getCode()).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> unauthorized(UnauthorizedException exception, HttpServletRequest request) {
        StandardError error = StandardErrorFactory.create(exception, request);
        return ResponseEntity.status(exception.getCode()).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardError> forbidden(ForbiddenException exception, HttpServletRequest request) {
        StandardError error = StandardErrorFactory.create(exception, request);
        return ResponseEntity.status(exception.getCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ValidationError error = new ValidationError(LocalDateTime.now(), exception.getStatusCode().value(), exception.getMessage(), request.getRequestURI());

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            error.addErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
