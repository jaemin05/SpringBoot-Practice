package com.example.socket.entity.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name ="chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String message;

    private String senderId;

    @ManyToOne
    @JsonBackReference
    private Room room;

    private ChatType chatType;

    @CreatedDate
    private LocalDateTime createAt;

    @NotNull
    private boolean isDeleted;

    public Chat deleteMessage() {
        this.message = "삭제된 메시지입니다";
        this.isDeleted = true;
        return this;
    }

    public Chat chattingRoomSet() {
        this.isDeleted = true;
        return this;
    }

}
