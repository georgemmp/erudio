package br.com.erudio.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Getter
    private Long id;

    private String userName;

    @Getter
    private String fullName;

    private String password;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private List<Permission> permissions;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        for (Permission permission: permissions) {
            roles.add(permission.getDescription());
        }

        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUserName() {
        return userName;
    }
}
