package br.com.erudio.application.usecases.user;

import br.com.erudio.domain.entities.user.User;
import br.com.erudio.domain.repositories.PasswordEncoderRepository;
import br.com.erudio.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderRepository passwordEncoderRepository;

    public User execute(User user) {
        String password = this.passwordEncoderRepository.encodePassword(user.getPassword());
        User saved = User.builder()
                .userName(user.getUserName())
                .password(password)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .fullName(user.getFullName())
                .enabled(true)
                .build();

        return this.userRepository.saveUser(saved);
    }
}
