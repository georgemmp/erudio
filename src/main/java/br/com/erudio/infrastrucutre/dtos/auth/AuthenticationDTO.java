package br.com.erudio.infrastrucutre.dtos.auth;

import br.com.erudio.application.validations.authentication.AuthenticationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AuthenticationRequest
public class AuthenticationDTO {

    private String userName;
    private String password;
}
