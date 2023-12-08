package br.com.erudio.domain.repositories;

import br.com.erudio.domain.entities.user.User;

import java.util.Optional;

public interface UserRepository {

    User findByUserName(String userName);

    User saveUser(User user);
}
