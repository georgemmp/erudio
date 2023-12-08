package br.com.erudio.infrastrucutre.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
