package com.example.socket.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LeaveChatRoomRequest {
    private String roomId;
}
