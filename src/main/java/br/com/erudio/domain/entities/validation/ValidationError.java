package br.com.erudio.domain.entities.validation;

import br.com.erudio.domain.entities.validation.FieldMessage;
import br.com.erudio.domain.entities.validation.StandardError;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void addErrorMessage(String fieldName, String message) {
        this.fieldMessages.add(new FieldMessage(fieldName, message));
    }
}
