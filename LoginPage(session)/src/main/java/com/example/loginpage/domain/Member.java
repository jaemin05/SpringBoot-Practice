package com.example.loginpage.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor //파라미터가 없는 생성자 생성
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue //id자동 생성
    private Long id;
    private String username;
    private String password;

    @Builder //자동으로 빌더 생성
    public Member(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
