package com.example.socket.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.socket.service.SocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class SocketController {
    private final SocketIOServer server;
    private final SocketService socketService;

    @PostConstruct
    public void socket(){
        server.addConnectListener(socketService::connect);

        server.addDisconnectListener(socketService::disconnect);

        server.addEventListener("joinRoom", String.class,(client, data, ackSender) -> {
            socketService.joinRoom(client,data);
        });

        server.addEventListener("joinUserRoom", String.class, (client, data, ackSender) -> {
            socketService.joinUserRoom(client,data);
        });

        server.addEventListener("leaveRoom", String.class, ((client, data, ackSender) -> {
            socketService.leaveRoom(client,data);
        }));

        server.addEventListener("sendMessage", String.class, (client, data, ackSender) -> {
            socketService.sendMessage(client,data);
        });

        server.addEventListener("sendNotice", String.class, (client, data, ackSender) -> {
            socketService.sendNotice(client,data);
        });

        server.addEventListener("changeTitle", String.class, (client, data, ackSender) -> {
            socketService.changeTitle(client,data);
        });
    }
}
