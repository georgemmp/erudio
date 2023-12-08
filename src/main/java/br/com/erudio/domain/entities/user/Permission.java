package br.com.erudio.domain.entities.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements GrantedAuthority {

    private Long id;

    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
}
