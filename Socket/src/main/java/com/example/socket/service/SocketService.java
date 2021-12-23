package com.example.socket.service;

import com.corundumstudio.socketio.SocketIOClient;

public interface SocketService {
    void connect(SocketIOClient client);
    void disconnect(SocketIOClient client);
    void joinRoom(SocketIOClient client, String json);
    void joinUserRoom(SocketIOClient client, String json);
    void leaveRoom(SocketIOClient client, String json);
    void sendMessage(SocketIOClient client, String json);
    void changeTitle(SocketIOClient client, String json);
    void sendNotice(SocketIOClient client, String json);
}
