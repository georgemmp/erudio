package br.com.erudio.mocks;

import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;

import static br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO.*;

public class AuthenticationDTOFactory {

    private AuthenticationDTOFactory() {
    }

    public static AuthenticationDTOBuilder craeteAuthetincationDTO() {
        return AuthenticationDTO.builder()
                .password("123456")
                .userName("admin");
    }
}
