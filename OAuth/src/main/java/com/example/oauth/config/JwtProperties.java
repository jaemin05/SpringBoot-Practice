package com.example.oauth.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secretToken;
    private final long accessTokenExp;
    private final long refreshTokenExp;

    public JwtProperties(String secretToken, long accessTokenExp, long refreshTokenExp){
        this.secretToken = Base64.getEncoder().encodeToString(secretToken.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
    }
}
