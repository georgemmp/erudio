package br.com.erudio.infrastrucutre.mapper;

import br.com.erudio.domain.entities.auth.Authentication;
import br.com.erudio.domain.entities.auth.Token;
import br.com.erudio.infrastrucutre.dtos.auth.AuthenticationDTO;
import br.com.erudio.infrastrucutre.dtos.auth.TokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticationMapper {

    TokenDTO tokenToTokenDTO(Token token);
    Authentication authenticationDTOtoAuthentication(AuthenticationDTO authenticationDTO);
}
