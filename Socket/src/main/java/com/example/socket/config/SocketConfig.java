package com.example.socket.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConfig {
    private SocketIOServer server;

    @Value("${server.socket.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration configuration = new Configuration();
        configuration.setPort(port);
        configuration.setTransports(Transport.POLLING, Transport.WEBSOCKET);
        configuration.setOrigin("*");

        SocketIOServer server = new SocketIOServer(configuration);
        this.server = server;
        return server;
    }

}
