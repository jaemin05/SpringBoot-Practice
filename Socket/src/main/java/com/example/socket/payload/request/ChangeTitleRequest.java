package com.example.socket.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ChangeTitleRequest {
    private String title;
    private String roomId;
}
