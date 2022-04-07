package com.example.oauth.global.oauth.info.impl;

import com.example.oauth.global.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImageUrl) {
        super(attributes, nameAttributeKey, name, email, profileImageUrl);
    }

    public static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {

        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        return GoogleOAuth2UserInfo.builder()
                .name((String) properties.get("nickname"))
                .email((String) attributes.get("account_email"))
                .profileImageUrl((String) properties.get("thumbnail_image"))
                .attributes(attributes)
                .nameAttributeKey("id")
                .build();
    }
}
