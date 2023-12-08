package br.com.erudio.domain.interfaces;

import org.springframework.http.HttpStatus;

public interface CustomizedException {

    String getMessage();

    HttpStatus getCode();
}
