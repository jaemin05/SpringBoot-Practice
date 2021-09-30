package com.example.test.Dto;

import com.example.test.Controller.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class memberDto {
    private Long id;
    private String username;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public memberDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
