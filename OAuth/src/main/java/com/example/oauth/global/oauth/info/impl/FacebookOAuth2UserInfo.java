package com.example.oauth.global.oauth.info.impl;

import com.example.oauth.global.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {

    public FacebookOAuth2UserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImageUrl) {
        super(attributes, nameAttributeKey, name, email, profileImageUrl);
    }

    public static OAuth2UserInfo ofFacebook(Map<String, Object> attributes) {
        return FacebookOAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImageUrl((String) attributes.get("imageUrl"))
                .attributes(attributes)
                .nameAttributeKey("id")
                .build();
    }
}
