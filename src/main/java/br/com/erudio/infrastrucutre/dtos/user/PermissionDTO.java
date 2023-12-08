package br.com.erudio.infrastrucutre.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDTO {

    private Long id;

    private String description;
}
