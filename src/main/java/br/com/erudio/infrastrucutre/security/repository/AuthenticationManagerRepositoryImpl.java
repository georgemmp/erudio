package br.com.erudio.infrastrucutre.security.repository;

import br.com.erudio.domain.repositories.AuthenticationManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerRepositoryImpl implements AuthenticationManagerRepository {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Authentication exccuteAuthentication(String userName, String password) {
        return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }
}
