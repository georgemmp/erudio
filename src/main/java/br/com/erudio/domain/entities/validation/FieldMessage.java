package br.com.erudio.domain.entities.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FieldMessage {

    private String fieldMessage;
    private String message;
}
