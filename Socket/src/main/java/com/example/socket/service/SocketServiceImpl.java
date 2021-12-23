package com.example.socket.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.socket.entity.Member;
import com.example.socket.entity.Role;
import com.example.socket.entity.chat.Room;
import com.example.socket.entity.chat.RoomType;
import com.example.socket.error.ErrorResponse;
import com.example.socket.exception.MemberNotFoundException;
import com.example.socket.payload.request.JoinChatRoomRequest;
import com.example.socket.repository.ChatRepository;
import com.example.socket.repository.MemberRepository;
import com.example.socket.repository.RoomRepository;
import com.example.socket.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService{
    private final SocketIOServer server;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;

    //JSON 컨텐츠를 Java 객체로 deserialization 하거나 Java 객체를 JSON으로 serialization 할 때 사용하는 Jackson 라이브러리의 클래스이다.
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void connect(SocketIOClient client){
        String token = client.getHandshakeData().getSingleUrlParam("Authorization");

        if(token.isBlank()) {
            errorAndDisconnected(client, "Invalid Token", 404);
            return;
        }
        Member member;
        try{
            System.out.println(client.getHandshakeData().getSingleHeader("Authorization"));
            System.out.println(client.getHandshakeData().getSingleHeader("test"));
            member = memberRepository.findById(tokenProvider.getUserSubject(token))
                    .orElseThrow(() -> MemberNotFoundException.Exception);
            logging(member.getId());
            client.set("member", member.getId());
            for(Room room:roomRepository.findRoomsByMembersContaining(member)){
                client.joinRoom(room.getId().toString());
            }
            logging("Connected: " + member.getName() + "SessionId: " + client.getSessionId());
        }catch (Exception e) {
            errorAndDisconnected(client, "User Not Found", 404);
        }
    }

    @Override
    public void disconnect(SocketIOClient client) {
        errorAndDisconnected(client, "Success to disconnect", 204);
    }

    @Override
    @Transactional
    public void joinRoom(SocketIOClient client, String json) {
        String memberId = client.get("member");
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) {
            errorAndDisconnected(client, "User Not Found", 404);
            return;
        }
        JoinChatRoomRequest joinChatRoomRequest;
        try{
            joinChatRoomRequest = objectMapper.readValue(json, JoinChatRoomRequest.class);
        } catch(Exception e){
            errorAndDisconnected(client, "User Not Found", 404);
            return;
        }

        if(joinChatRoomRequest != null){
            Room room = null;
            if(!(joinChatRoomRequest.getRoomId()==null)){
                room = roomRepository.findById(joinChatRoomRequest.getRoomId())
                        .orElse(null);
            }
            String title;
            Role authority;
            RoomType category;

            if(room != null){
                if(roomRepository.existsByIdAndMembersContaining(room.getId(), member)) {
                    errorAndDisconnected(client, "유저가 이미 존재합니다", 404);
                    return;
                }
                title=room.getName();
                authority=Role.ROLE_USER;
                category=room.getRoomType();
                roomRepository.save(room.memberAdd(member));
            }else {
                errorAndDisconnected(client,"채팅방이 존재하지 않습니다", 404);
            }
            
        }
    }

    @Override
    public void joinUserRoom(SocketIOClient client, String json) {

    }

    @Override
    public void leaveRoom(SocketIOClient client, String json) {

    }

    @Override
    public void sendMessage(SocketIOClient client, String json) {

    }

    @Override
    public void changeTitle(SocketIOClient client, String json) {

    }

    @Override
    public void sendNotice(SocketIOClient client, String json) {

    }

    private void logging(String message) {
        log.info(message);
    }
    private void errorAndDisconnected(SocketIOClient client, String message, int status) {
        client.sendEvent("error",
                ErrorResponse.builder()
                        .status(status)
                        .message(message)
                        .build()
        );
        log.error("Status" + status + "Message" + message);
        client.disconnect();
    }
}