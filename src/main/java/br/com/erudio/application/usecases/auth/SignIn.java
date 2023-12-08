package br.com.erudio.application.usecases.auth;

import br.com.erudio.application.exceptions.AbstractExceptionFactory;
import br.com.erudio.application.exceptions.NotFoundExceptionFactory;
import br.com.erudio.application.exceptions.UnauthorizedExceptionFactory;
import br.com.erudio.domain.entities.auth.Authentication;
import br.com.erudio.domain.entities.auth.Token;
import br.com.erudio.domain.entities.user.User;
import br.com.erudio.domain.repositories.AuthenticationManagerRepository;
import br.com.erudio.domain.repositories.JwtRepository;
import br.com.erudio.domain.repositories.UserRepository;
import br.com.erudio.domain.types.ApiMessagesException;
import br.com.erudio.domain.types.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.erudio.domain.types.ApiMessagesException.*;
import static br.com.erudio.domain.types.ExceptionType.*;

@Service
public class SignIn {

    @Autowired
    private JwtRepository jwtRepository;

    @Autowired
    private AuthenticationManagerRepository authenticationManagerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractExceptionFactory exceptionFactory;

    public Token execute(Authentication authentication) {
        try {
            this.authenticationManagerRepository.exccuteAuthentication(authentication.getUserName(), authentication.getPassword());
            User user = this.userRepository.findByUserName(authentication.getUserName());

            return this.jwtRepository.createAccessToken(authentication.getUserName(), user.getRoles());
        } catch (Exception e) {
            throw this.exceptionFactory.create(UNAUTHORIZED, INVALID_CREDENTIALS);
        }
    }
}
