package com.example.fcmpractice.domain.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 5, max = 10, message = "아이디는 영문자와 숫자를 모두 포함해서 5~10자리 이내로 입력해주세요.")
    private String accountId;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$",
            message = "비밀번호는 영문자와 숫자, 특수문자를 모두 포함해서 6~18자리 이내로 입력해주세요.")
    private String password;
}
