package com.example.oauth.domain.refreshToken;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity
public class UserRefreshToken {
    @Id
    @Column(name = "REFRESH_TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "REFRESH_TOKEN", length = 512)
    @NotNull
    @Size(max = 512)
    private String refreshToken;

    @Column(name = "REFRESH_EXP")
    private Long refreshExpiration;

    @Builder
    public UserRefreshToken(String userId, String refreshToken, Long refreshExpiration) {
        this.userId = userId;
        this. refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
    }

    public String reissue(String refreshToken, Long refreshExpiration){
        this.refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
        return this.refreshToken;
    }

}
