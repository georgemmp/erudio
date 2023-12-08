package br.com.erudio.infrastrucutre.data.repository.user;

import br.com.erudio.domain.entities.user.User;
import br.com.erudio.domain.repositories.UserRepository;
import br.com.erudio.infrastrucutre.data.datasource.user.UserJpaRepository;
import br.com.erudio.infrastrucutre.data.models.user.UserModel;
import br.com.erudio.infrastrucutre.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public User findByUserName(String userName) {
        UserModel userModel = this.jpaRepository.findUserByUserName(userName);
        return this.mapper.userModelToUser(userModel);
    }

    @Override
    public User saveUser(User user) {
        UserModel userModel = this.mapper.userToUserModel(user);
        UserModel saved = this.jpaRepository.save(userModel);
        return this.mapper.userModelToUser(saved);
    }
}
