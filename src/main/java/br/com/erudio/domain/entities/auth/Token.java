package br.com.erudio.domain.entities.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
