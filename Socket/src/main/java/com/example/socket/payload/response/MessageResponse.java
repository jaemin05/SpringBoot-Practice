package com.example.socket.payload.response;

import com.example.socket.entity.chat.ChatType;
import lombok.*;

@Getter
@Builder
public class MessageResponse {
    private String messageId;
    private String roomId;
    private String name;
    private String message;
    private ChatType chatType;
    private String createAt;
    private boolean isDeleted;
    private boolean isMine;
}
