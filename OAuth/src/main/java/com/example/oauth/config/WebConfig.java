package com.example.oauth.config;

import lombok.Builder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final long MAX_AGE = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry
                .addMapping("/**") //CORS 적용할 URL 패턴
                .allowedOrigins("*") // 자원을 공유할 오리진 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") //요청 허용 메서드
                .allowedHeaders("*") //요청 허용 헤더
                .allowCredentials(true) //쿠키허용
                .maxAge(MAX_AGE);
    }
}
