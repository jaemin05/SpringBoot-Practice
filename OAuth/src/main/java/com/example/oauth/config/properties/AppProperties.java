package com.example.oauth.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
//@ConfigurationProperties: properties 파일의 key 값이 아래와 같이 같은 값으로 시작할 때, 그것을 묶어서 Bean으로 등록할 수 있다.
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Auth{
        private String tokenSecret;
        private long accessTokenExpiry;
        private long refreshTokenExpiry;
    }

    public static class OAuth{
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris(){
            return authorizedRedirectUris;
        }

        public OAuth authorizedRedirectUris(List<String> authorizedRedirectUris){
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
