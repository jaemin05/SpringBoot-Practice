package com.example.oauth.security.oauth.user.impl;

import com.example.oauth.security.oauth.user.OAuthUserInfo;

import java.util.Map;

public class NaverOAuthUserInfo extends OAuthUserInfo {
    public NaverOAuthUserInfo(Map<String, Object> attributes){
        super(attributes);
    }
    @Override
    public String getId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null){
            return null;
        }

        return (String) response.get("id");
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null){
            return null;
        }

        return (String) response.get("nickname");
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null){
            return null;
        }

        return (String) response.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null){
            return null;
        }

        return (String) response.get("profile_image");
    }
}
