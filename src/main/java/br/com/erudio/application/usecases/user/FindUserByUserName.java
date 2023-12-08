package br.com.erudio.application.usecases.user;

import br.com.erudio.application.exceptions.NotFoundExceptionFactory;
import br.com.erudio.domain.entities.user.User;
import br.com.erudio.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.erudio.domain.types.ApiMessagesException.USER_NOT_FOUND;

@Service
public class FindUserByUserName implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private NotFoundExceptionFactory notFoundExceptionFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUserName(username);

        if (Objects.isNull(user)) {
            throw this.notFoundExceptionFactory.create(USER_NOT_FOUND);
        }

        return user;
    }
}
