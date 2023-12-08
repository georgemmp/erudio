package br.com.erudio.infrastrucutre.adapter.user;

import br.com.erudio.application.usecases.user.SaveUser;
import br.com.erudio.domain.entities.user.User;
import br.com.erudio.infrastrucutre.dtos.user.UserRequestDTO;
import br.com.erudio.infrastrucutre.dtos.user.UserResponseDTO;
import br.com.erudio.infrastrucutre.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveUserAdapter {

    @Autowired
    private SaveUser saveUser;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {
        User user = this.userMapper.userRequestDTOtoUser(userRequestDTO);
        User saved = this.saveUser.execute(user);
        return this.userMapper.userToUserResponseDTO(saved);
    }
}
