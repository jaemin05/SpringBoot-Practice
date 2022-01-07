package com.example.oauth.security.oauth.user;

import java.util.Map;

//abstract: 추상
//추상적으로 정보를 모아두고 구체적으로는 페이스북, 네이버, 구글 등으로 따로 만듬
public abstract class OAuthUserInfo {
    protected Map<String, Object> attributes;

    public OAuthUserInfo(Map<String, Object> attributes ){
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes(){
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
