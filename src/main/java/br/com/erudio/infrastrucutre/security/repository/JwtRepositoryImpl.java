package br.com.erudio.infrastrucutre.security.repository;

import br.com.erudio.application.exceptions.AbstractExceptionFactory;
import br.com.erudio.domain.entities.auth.Token;
import br.com.erudio.domain.repositories.JwtRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static br.com.erudio.domain.types.ApiMessagesException.INVALID_TOKEN;
import static br.com.erudio.domain.types.ExceptionType.FORBIDDEN;

@Component
public class JwtRepositoryImpl implements JwtRepository {

    @Value("${security.jwt.token.secret-key:secretKey}")
    private String secretKey;

    @Value("${security.jwt.token. expire-length:3600000}")
    private Long validityInMilliSeconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AbstractExceptionFactory exceptionFactory;

    private Algorithm algorithm = null;

    private static final String BEARER = "Bearer ";

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        this.algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
    }

    @Override
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = this.decodedJWT(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(token) && token.startsWith(BEARER)) {
            return token.substring(BEARER.length());
        }

        return null;
    }

    @Override
    public Token createAccessToken(String userName, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validityInMilliSeconds);
        String accessToken = this.getAccessToken(userName, roles, now, validity);
        String refreshToken = this.getRefreshToken(userName, roles, now);

        return Token.builder()
                .userName(userName)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .created(now)
                .expiration(validity)
                .authenticated(true)
                .build();
    }

    @Override
    public Token createRefreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) {
            refreshToken = refreshToken.substring("Bearer ".length());
        }

        DecodedJWT decodedJWT = this.decodedJWT(refreshToken);
        String userName = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return this.createAccessToken(userName, roles);
    }

    @Override
    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = this.decodedJWT(token);
        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw this.exceptionFactory.create(FORBIDDEN, INVALID_TOKEN);
        }
    }

    private String getAccessToken(String userName, List<String> roles, Date now, Date validity) {
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("role", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userName)
                .withIssuer(issueUrl)
                .sign(this.algorithm)
                .strip();
    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {
        Date validity = new Date(now.getTime() + (this.validityInMilliSeconds * 3));

        return JWT.create()
                .withClaim("role", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userName)
                .sign(this.algorithm)
                .strip();
    }

    private DecodedJWT decodedJWT(String token) {
        JWTVerifier verifier = JWT.require(this.algorithm).build();
        return verifier.verify(token);
    }
}
