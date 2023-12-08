package br.com.erudio.infrastrucutre.data.models.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "permission")
public class PermissionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String description;
}
