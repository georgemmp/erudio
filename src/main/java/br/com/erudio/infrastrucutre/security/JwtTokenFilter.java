package br.com.erudio.infrastrucutre.security;

import br.com.erudio.domain.repositories.JwtRepository;
import br.com.erudio.infrastrucutre.security.repository.JwtRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtRepository jwtRepositoryImpl;

    public JwtTokenFilter(JwtRepositoryImpl jwtRepositoryImpl) {
        this.jwtRepositoryImpl = jwtRepositoryImpl;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtRepositoryImpl.resolveToken((HttpServletRequest) request);

        if (Objects.nonNull(token) && jwtRepositoryImpl.validateToken(token)) {
            Authentication authentication = this.jwtRepositoryImpl.getAuthentication(token);

            if (Objects.nonNull(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
