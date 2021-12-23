package com.example.socket.service;

import com.corundumstudio.socketio.SocketIOServer;

public interface SocketService {
    void connect(SocketIOServer client);
    void disconnect(SocketIOServer client);
    void joinRoom(SocketIOServer client, String json);
    void joinUserRoom(SocketIOServer client, String json);
    void leaveRoom(SocketIOServer client, String json);
    void sendMessage(SocketIOServer client, String json);
    void changeTitle(SocketIOServer client, String json);
    void sendNotice(SocketIOServer client, String json);
}
