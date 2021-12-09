package com.example.loginpage.Dto;

import com.example.loginpage.domain.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
