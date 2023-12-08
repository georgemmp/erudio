package br.com.erudio.infrastrucutre.adapter.auth;

import br.com.erudio.application.usecases.auth.SignIn;
import br.com.erudio.domain.entities.auth.Authentication;
import br.com.erudio.domain.entities.auth.Token;
import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;
import br.com.erudio.infrastrucutre.dtos.auth.TokenDTO;
import br.com.erudio.infrastrucutre.mapper.AuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInAdapter {

    @Autowired
    private SignIn signIn;

    @Autowired
    private AuthenticationMapper mapper;

    public TokenDTO execute(AuthenticationDTO authenticationDTO) {
        Authentication authentication = this.mapper.authenticationDTOtoAuthentication(authenticationDTO);
        Token token = this.signIn.execute(authentication);
        return this.mapper.tokenToTokenDTO(token);
    }
}
