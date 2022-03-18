package com.example.fcmpractice.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class NotificationRequest {
    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 20, message = "title은 20자 까지 작성하실 수 있습니다.")
    private String title;

    @Size(max = 200, message = "content는 200자 까지 작성하실 수 있습니다.")
    private String content;
}
