package br.com.erudio.application.usecases.auth;

import br.com.erudio.application.exceptions.AbstractExceptionFactory;
import br.com.erudio.application.exceptions.ForbiddenExceptionFactory;
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

import java.util.Objects;

import static br.com.erudio.domain.types.ApiMessagesException.*;
import static br.com.erudio.domain.types.ExceptionType.*;

@Service
public class RefreshToken {

    @Autowired
    private JwtRepository jwtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbstractExceptionFactory exceptionFactory;

    public Token execute(String userName, String refreshToken) {
        if (Objects.isNull(userName) || Objects.isNull(refreshToken)) {
            throw this.exceptionFactory.create(FORBIDDEN, INVALID_CREDENTIALS);
        }

        User user = this.userRepository.findByUserName(userName);

        if(Objects.isNull(user)) {
            throw this.exceptionFactory.create(FORBIDDEN, INVALID_CREDENTIALS);
        }

        return this.jwtRepository.createRefreshToken(refreshToken);
    }
}
