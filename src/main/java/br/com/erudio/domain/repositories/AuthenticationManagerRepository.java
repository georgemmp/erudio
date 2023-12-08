package br.com.erudio.domain.repositories;

import org.springframework.security.core.Authentication;

public interface AuthenticationManagerRepository {

    Authentication exccuteAuthentication(String userName, String password);
}
