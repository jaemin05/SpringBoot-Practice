package com.example.oauth.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
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

    @Builder
    public UserRefreshToken(String userId, String refreshToken) {
        this.userId = userId;
        this. refreshToken = refreshToken;
    }

    public UserRefreshToken updateValue(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }

}
