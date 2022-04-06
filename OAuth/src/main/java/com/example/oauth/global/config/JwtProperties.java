package com.example.oauth.global.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final Auth auth = new Auth();

    @Getter
    public static class Auth {
        private String secretToken;
        private long accessTokenExp;
        private long refreshTokenExp;
    }
}