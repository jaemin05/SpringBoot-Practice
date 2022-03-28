package com.example.fcmpractice.domain.notice.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class NoticeRequest {
    @NotBlank(message = "title은 필수 입력입니다.")
    @Size(min = 1, max = 30, message = "title은 1~30자리 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "content는 필수 입력입니다.")
    private String content;
}
