package com.example.socket.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.socket.entity.Member;
import com.example.socket.entity.Role;
import com.example.socket.entity.chat.*;
import com.example.socket.error.ErrorResponse;
import com.example.socket.exception.MemberNotFoundException;
import com.example.socket.payload.request.*;
import com.example.socket.payload.response.MessageResponse;
import com.example.socket.repository.ChatRepository;
import com.example.socket.repository.MemberRepository;
import com.example.socket.repository.RoomRepository;
import com.example.socket.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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
            client.joinRoom(String.valueOf(room.getId()));
            logging(member.getName() + "님이 " + room.getName() + "에 입장하였습니다");

            Chat chat = chatRepository.save(
                    Chat.builder()
                            .room(room)
                            .message(member.getName() + "님이 입장하였습니다")
                            .chatType(ChatType.SYSTEM)
                            .createAt(LocalDateTime.now())
                            .senderId(member.getId())
                            .build()
            );
            sendSys(chat, room);
        }else {
            errorAndDisconnected(client, "Bad Request", 400);
        }
    }

    @Override
    @Transactional
    public void joinUserRoom(SocketIOClient client, String json) {
        String memberId = client.get("member");
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null){
            errorAndDisconnected(client, "User Not Found", 404);
            return;
        }

        JoinUserRoomRequest joinUserRoomRequest;

        try{
            joinUserRoomRequest = objectMapper.readValue(json, JoinUserRoomRequest.class);
        }catch (Exception e) {
            errorAndDisconnected(client, "Bad Request", 400);
            return;
        }

        if(joinUserRoomRequest != null) {
            Room room = null;
            if(!(joinUserRoomRequest.getRoomId()==null)){
                room = roomRepository.findById(joinUserRoomRequest.getRoomId()).orElse(null);
            }
            String title;
            Role authority;
            RoomType chatType;

            if(room != null){
                if(memberRepository.findByIdAndUserContaining(room.getId(), joinUserRoomRequest.getUserPk()).isEmpty()){
                    errorAndDisconnected(client, "Member Not Exists", 404);
                }
                if(roomRepository.existsByIdAndMembersContaining(room.getId(), member)){
                    errorAndDisconnected(client, "유저가 이미 존재합니다", 404);
                    return;
                }
                title = room.getName();
                authority = Role.ROLE_USER;
                chatType = room.getRoomType();
                roomRepository.save(room.memberAdd(member));
            } else {
                room = roomRepository.save(
                        Room.builder()
                                .id(String.valueOf(UUID.randomUUID()))
                                .name("채탱방")
                                .category(MeetingCategory.ELSE)
                                .admin(memberId)
                                .info(memberId + "님의 채팅방")
                                .onOffline(OnOffline.ONLINE)
                                .roomType(RoomType.USER)
                                .build().memberAdd(member)
                );
            }
            client.joinRoom(String.valueOf(room.getId()));
            logging(member.getName() + "님이 " + room.getName() + "에 참여하셨습니다");

            Chat chat = chatRepository.save(
                    Chat.builder()
                            .room(room)
                            .message(member.getName() + "님이 참여하였습니다")
                            .chatType(ChatType.SYSTEM)
                            .createAt(LocalDateTime.now())
                            .senderId(member.getId())
                            .build()
            );
            sendSys(chat, room);
        }
        else{
            errorAndDisconnected(client, "Bad Request" ,404);
        }
    }

    @Override
    @Transactional
    public void leaveRoom(SocketIOClient client, String json) {
        String memberId = client.get("member");
        Member member = memberRepository.findById(memberId).orElse(null);

        LeaveChatRoomRequest leaveChatRoomRequest = null;

        try{
            leaveChatRoomRequest = objectMapper.readValue(json, LeaveChatRoomRequest.class);
        }catch (Exception e){
            errorAndDisconnected(client, "Bad Request", 404);
        }

        if(!client.getAllRooms().contains(leaveChatRoomRequest.getRoomId())){
            errorAndDisconnected(client, "Room Not Exists", 400);
            return;
        }

        Room room = roomRepository.findById(leaveChatRoomRequest.getRoomId()).orElse(null);

        if(roomRepository.existsByIdAndAdmin(leaveChatRoomRequest.getRoomId(), member.getId())){
            sendSys(chatRepository.save(
                    Chat.builder()
                            .room(room)
                            .message("관리자" + member.getName() + "님이 채팅방에서 나가셨습니다. 잠시 뒤 이 채팅방은 사라집니다")
                            .createAt(LocalDateTime.now())
                            .chatType(ChatType.SYSTEM)
                            .senderId(member.getId())
                            .build()
            ), room);
            roomRepository.save(room.roomDelete());
            deleteRoom(leaveChatRoomRequest.getRoomId());
            roomRepository.deleteById(leaveChatRoomRequest.getRoomId());
        } else{
            if(room !=null) {
                Chat chat = chatRepository.save(
                        Chat.builder()
                                .room(room)
                                .message(member.getName() +  "님이 채팅방을 떠났습니다")
                                .createAt(LocalDateTime.now())
                                .chatType(ChatType.SYSTEM)
                                .senderId(member.getId())
                                .build()
                );
                roomRepository.save(room.memberDelete(member));
                client.leaveRoom(leaveChatRoomRequest.getRoomId().toString());
                sendSys(chat, room);
            }else {
                errorAndDisconnected(client, "Room Not Found", 404);
            }
        }
    }

    @Override
    @Transactional
    public void sendMessage(SocketIOClient client, String json) {
        String memberId = client.get("member");
        Member member = memberRepository.findById(memberId).orElse(null);

        MessageRequest messageRequest;

        try{
            messageRequest = objectMapper.readValue(json, MessageRequest.class);
        } catch (Exception e){
            errorAndDisconnected(client, "Bad Request", 404);
            return;
        }

        if(messageRequest != null){
            if(member == null){
                errorAndDisconnected(client, "Member Not Found", 404);
                return;
            }

            if(!roomRepository.existsByIdAndMembersContaining(messageRequest.getRoomId(), member)){
                errorAndDisconnected(client, "Room Not Found", 404);
                return;
            }

            Room room = roomRepository.findById(messageRequest.getRoomId()).orElse(null);

            if(room != null){
                Chat chat = chatRepository.save(
                        Chat.builder()
                                .senderId(member.getId())
                                .chatType(ChatType.USER)
                                .room(room)
                                .message(messageRequest.getMessage())
                                .createAt(LocalDateTime.now())
                                .isDeleted(false)
                                .build()
                );

                send(member, chat, room);
            }else {
                errorAndDisconnected(client, "Bad Request", 400);
            }
        }else {
            errorAndDisconnected(client, "Bad Request", 400);
        }
    }

    @Override
    @Transactional
    public void changeTitle(SocketIOClient client, String json) {
        ChangeTitleRequest changeTitleRequest = null;
        String memberId = client.get("member");

        try{
            changeTitleRequest = objectMapper.readValue(json, ChangeTitleRequest.class);
        } catch (Exception e){
            errorAndDisconnected(client,"Bad Request", 404);
        }

        Room room = roomRepository.findByIdAndAdmin(changeTitleRequest.getRoomId(),memberId).orElse(null);
    }

    @Override
    public void sendNotice(SocketIOClient client, String json) {

    }

    private void send(Member member, Chat chat, Room room){
        server.getRoomOperations(room.getId().toString()).sendEvent("message",
                MessageResponse.builder()
                        .messageId(chat.getId().toString())
                        .name(member.getName())
                        .message(chat.getMessage())
                        .createAt(LocalDateTime.now().toString())
                        .roomId(chat.getRoom().getId().toString())
                        .messageType(chat.getChatType())
                        .isMine(member.equals(chat.getSenderId()))
                        .isDeleted(false)
                        .build());
        roomRepository.save(room.chatCountAdd());
    }

    private void deleteRoom(String roomId){
        for(SocketIOClient client : server.getRoomOperations(roomId).getClients()){
            client.leaveRoom(roomId);
        }
    }

    private void sendSys(Chat chat, Room rooms){
        server.getRoomOperations(chat.getRoom().getId().toString())
                .sendEvent("info",
                        MessageResponse.builder()
                                .name(ChatType.SYSTEM.toString())
                                .messageId(chat.getId().toString())
                                .messageType(ChatType.SYSTEM)
                                .roomId(chat.getRoom().getId().toString())
                                .message(chat.getMessage())
                                .isDeleted(chat.isDeleted())
                                .createAt(LocalDateTime.now().toString())
                                .build()
                );
        roomRepository.save(rooms.chatCountAdd());
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