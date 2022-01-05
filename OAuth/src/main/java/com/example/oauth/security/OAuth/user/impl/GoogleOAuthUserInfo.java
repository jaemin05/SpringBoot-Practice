package com.example.oauth.security.OAuth.user.impl;

import com.example.oauth.security.OAuth.user.OAuthUserInfo;

import java.util.Map;

public class GoogleOAuthUserInfo extends OAuthUserInfo {
    public GoogleOAuthUserInfo(Map<String, Object> attributes){
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
