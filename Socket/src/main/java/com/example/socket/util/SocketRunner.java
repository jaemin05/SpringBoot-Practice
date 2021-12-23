package com.example.socket.util;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(1)
//@Order: Bean 생성 순서를 결정, (1): 1번째로 생성
public class SocketRunner implements CommandLineRunner {
    private final SocketIOServer server;

    // @Component 어노테이션을 선언해두면 컴포넌트 스캔이되고 구동 시점에 run 메소드의 코드가 실행됩니다.
    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
