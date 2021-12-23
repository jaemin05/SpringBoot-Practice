package com.example.socket.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ROOM_NOT_FOUND(500, "ChattingRoom Not Found"),
    MEMBER_NOT_FOUND(500, "Member Not Found");

    private int status;
    private String message;

    public int getStatus() {
        return 0;
    }
}
