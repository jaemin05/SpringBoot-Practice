package com.example.searchandpaging.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//@JsonFormat: JSON 응답값의 형식을 지정할 때 사용한다.
//리턴받는 기댓값은 객체 형태이기 때문에 Shape.OBJECT로 설정
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    private final int status;
    private final String message;
}
