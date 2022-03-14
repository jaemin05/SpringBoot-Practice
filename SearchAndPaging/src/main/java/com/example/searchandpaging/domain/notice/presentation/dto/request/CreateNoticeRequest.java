package com.example.searchandpaging.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CreateNoticeRequest {
    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 20, message = "title은 1~20자 까지 작성하실 수 있습니다.")
    private String title;

    @Size(max = 200, message = "content는 200자 까지 작성하실 수 있습니다.")
    private String content;

}
