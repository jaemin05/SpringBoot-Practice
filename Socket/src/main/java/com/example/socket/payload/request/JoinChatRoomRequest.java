package com.example.socket.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class JoinChatRoomRequest {
    private String roomId;
    private String chatCategory;
}
