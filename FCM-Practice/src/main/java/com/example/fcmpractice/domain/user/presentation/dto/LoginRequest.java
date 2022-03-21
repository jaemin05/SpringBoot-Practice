package com.example.fcmpractice.domain.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="[a-zA-Z1-9]{5,10}$", message = "아이디는 영문자와 숫자를 모두 포함해서 5~10자리 이내로 입력해주세요.")
    private String accountId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$",
            message = "비밀번호는 영문자와 숫자, 특수문자를 모두 포함해서 6~18자리 이내로 입력해주세요.")
    private String password;
}
