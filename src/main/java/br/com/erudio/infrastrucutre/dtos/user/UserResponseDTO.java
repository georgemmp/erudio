package br.com.erudio.infrastrucutre.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserResponseDTO {

    private String userName;

    private String fullName;
}
