package br.com.erudio.domain.repositories;

import br.com.erudio.domain.entities.auth.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;

public interface JwtRepository {

    Authentication getAuthentication(String token);
    String resolveToken(HttpServletRequest request);
    boolean validateToken(String token);
    Token createAccessToken(String userName, List<String> roles);
    Token createRefreshToken(String refreshToken);
}
