package com.example.socket.payload.request;

import com.example.socket.entity.chat.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinUserRoomRequest {
    private String userPk;
    private String roomId;
    private RoomType roomType;
}
