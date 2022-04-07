package com.example.oauth.global.oauth.info.impl;

import com.example.oauth.global.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImageUrl) {
        super(attributes, nameAttributeKey, name, email, profileImageUrl);
    }

    public static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return GoogleOAuth2UserInfo.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .profileImageUrl((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey("id")
                .build();
    }
}
