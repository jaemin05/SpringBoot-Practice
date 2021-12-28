package com.example.socket.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {
    private String roomId;
    private String message;
}
