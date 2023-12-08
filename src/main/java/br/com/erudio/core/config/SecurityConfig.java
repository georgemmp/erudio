package br.com.erudio.core.config;

import br.com.erudio.infrastrucutre.security.JwtTokenFilter;
import br.com.erudio.infrastrucutre.security.repository.JwtRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        return passwordEncoder;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JwtRepositoryImpl jwtRepositoryImpl) throws Exception {
        JwtTokenFilter filter = new JwtTokenFilter(jwtRepositoryImpl);
        return http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized ->
                        authorized.requestMatchers("/api/v1/auth/**",
                                        "/api-docs/**",
                                        "/swagger-ui/index.html**",
                                        "/api/v1/users")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
