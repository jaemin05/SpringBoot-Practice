package com.example.socket.payload.request;

import com.example.socket.entity.chat.RoomType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeetingRequest {
        private String name;
        private RoomType roomType;
        private String admin;
        private String info;
}
