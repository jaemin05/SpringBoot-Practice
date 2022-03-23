package com.example.fcmpractice.domain.notice.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
public class NoticeRequest {
    @NotBlank(message = "title은 필수 입력입니다.")
    @Size(min = 1, max = 30, message = "title은 1~30자리 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "content는 필수 입력입니다.")
    private String content;
}
