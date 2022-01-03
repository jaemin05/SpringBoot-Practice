package com.example.oauth.api.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER_REFRESH_TOKEN")
public class UserRefreshToken {
    @Id
    @Column(name = "REFRESH_TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @Column(name = "REFRESH_TOKEN", length = 256)
    @NotNull
    @Size(max = 256)
    private String refreshToken;

    public UserRefreshToken(@NotNull @Size(max = 256) String refreshToken){
        this.refreshToken = refreshToken;
    }
}
