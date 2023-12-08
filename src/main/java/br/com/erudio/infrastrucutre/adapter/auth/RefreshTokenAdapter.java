package br.com.erudio.infrastrucutre.adapter.auth;

import br.com.erudio.application.usecases.auth.RefreshToken;
import br.com.erudio.application.usecases.auth.SignIn;
import br.com.erudio.domain.entities.auth.Authentication;
import br.com.erudio.domain.entities.auth.Token;
import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;
import br.com.erudio.infrastrucutre.dtos.auth.TokenDTO;
import br.com.erudio.infrastrucutre.mapper.AuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenAdapter {

    @Autowired
    private RefreshToken refreshToken;

    @Autowired
    private AuthenticationMapper mapper;

    public TokenDTO execute(String userName, String refreshToken) {
        Token token = this.refreshToken.execute(userName, refreshToken);
        return this.mapper.tokenToTokenDTO(token);
    }
}
