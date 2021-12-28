package com.example.socket.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MessageRequest {
    private String roomId;
    private String message;
}
