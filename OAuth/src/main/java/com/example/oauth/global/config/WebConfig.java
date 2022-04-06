package com.example.oauth.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") //CORS 적용할 URL 패턴
                .allowedOrigins("*") // 자원을 공유할 오리진 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") //요청 허용 메서드
                .allowedHeaders("*"); //요청 허용 헤더
    }
}
