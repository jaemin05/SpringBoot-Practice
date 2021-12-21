package com.example.socket.exception;

import com.example.socket.error.ErrorCode;
import com.example.socket.error.Exception.BusinessException;

public class RoomNotFoundException  extends BusinessException {
    public static BusinessException Exception =
            new RoomNotFoundException();

    private RoomNotFoundException() {
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}
