package com.example.oauth.config;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();

    @Getter
    @RequiredArgsConstructor
    public static class Auth{
        private String tokenSecret;
        private long tokenExpiry;
        private long refreshTokenExpiry;

        @Builder
        public Auth(String tokenSecret, long tokenExpiry, long refreshTokenExpiry){
            this.tokenSecret = tokenSecret;
            this.tokenExpiry = tokenExpiry;
            this.refreshTokenExpiry = refreshTokenExpiry;
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizeRedirectUris(List<String> authorizedRedirectUris){
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
