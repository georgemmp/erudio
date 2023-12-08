package br.com.erudio.infrastrucutre.mapper;

import br.com.erudio.domain.entities.user.User;
import br.com.erudio.infrastrucutre.data.models.user.UserModel;
import br.com.erudio.infrastrucutre.dtos.user.UserRequestDTO;
import br.com.erudio.infrastrucutre.dtos.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User userModelToUser(UserModel userModel);

    UserModel userToUserModel(User user);

    @Mappings(value = {
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "accountNonExpired", ignore = true),
        @Mapping(target = "accountNonLocked", ignore = true),
        @Mapping(target = "credentialsNonExpired", ignore = true),
        @Mapping(target = "enabled", ignore = true),
        @Mapping(target = "permissions", ignore = true),
    })
    User userRequestDTOtoUser(UserRequestDTO userRequestDTO);

    UserResponseDTO userToUserResponseDTO(User user);
}
