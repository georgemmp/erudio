package br.com.erudio.domain.repositories;

public interface PasswordEncoderRepository {

    String encodePassword(String password);
}
