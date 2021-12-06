package com.example.practice.Entity.member;

import com.example.practice.Entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable =false) //unique : 값이 유일
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @Builder
    public Member(String email, String password){
        this.email = email;
        this.password = password;
    }


}
