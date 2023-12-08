package br.com.erudio.infrastrucutre.security.repository;

import br.com.erudio.domain.repositories.PasswordEncoderRepository;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PasswordEncoderRepositoryImpl implements PasswordEncoderRepository {

    @Override
    public String encodePassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        return passwordEncoder.encode(password);
    }
}
