package br.com.erudio.infrastrucutre.dtos.user;

import br.com.erudio.application.validations.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@UserRequest
public class UserRequestDTO {

    private String userName;

    private String fullName;

    private String password;
}
