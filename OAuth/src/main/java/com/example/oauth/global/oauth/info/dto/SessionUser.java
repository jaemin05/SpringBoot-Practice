package com.example.oauth.global.oauth.info.dto;

import com.example.oauth.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String profileImageUrl;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
