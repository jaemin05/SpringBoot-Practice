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
    private final Auth auth = new Auth();

    @Getter
    public static class Auth {
        private String secretToken;
        private long accessTokenExp;
        private long refreshTokenExp;
    }



}
