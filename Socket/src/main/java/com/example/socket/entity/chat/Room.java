package com.example.socket.entity.chat;

import com.example.socket.entity.Member;
import com.example.socket.entity.UserInfoRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifyId;

    @NotNull
    private String id;

    private String name;

    @NotNull
    private RoomType roomType;

    @OneToMany
    @JsonBackReference
    private List<Image> profile;

    @NotNull
    private String admin;

    private String info;

    @NotNull
    private MeetingCategory category;

    @ManyToMany
    @JsonBackReference
    private List<Member> members;

    private LocalDateTime localDateTime;

    private OnOffline onOffline;

    private int chatCount;

    @OneToMany
    @JsonBackReference
    private List<Member> user;

    @OneToOne(cascade = CascadeType.ALL)
    private UserInfoRequest userInfoRequest;

    //mappedBy: 양방향 연관관계일때 반대쪽에 매핑되는 엔티티의 필드값을 줌
    //orphanRemoval: 기존 NULL처리된 자식을 DELETE 한다
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats;

    public Room chatCountAdd(){
        this.chatCount++;
        return this;
    }

    public Room changeRoomName(String roomName) {
        this.name = roomName;
        return this;
    }

    public Room memberAdd(Member member) {
        if(this.members == null) {
            this.members = new ArrayList<>();
        }
        this.members.add(member);
        return this;
    }

    public Room memberDelete(Member member) {
        this.members.remove(member);
        return this;
    }

    public void memberSet() {
        this.members.stream().map(member -> null);
    }

    public void chatSet() {
        this.chats.stream().map(chat -> chat.chattingRoomSet());
    }

    public Room roomDelete() {
        memberSet();
        chatSet();
        this.chats.stream().map(chat->null);
        return this;
    }

    public Image addProfile(Image profile) {
        this.profile.add(profile);
        return profile;
    }

    public Room applyChatRoom(Member member) {
        this.user.add(member);
        return this;
    }

    public Room setUserInfoRequest(UserInfoRequest userInfoRequest) {
        this.userInfoRequest = userInfoRequest;
        return this;
    }
}
