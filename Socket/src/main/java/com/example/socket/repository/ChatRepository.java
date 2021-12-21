package com.example.socket.repository;

import com.example.socket.entity.chat.Chat;
import com.example.socket.entity.chat.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByIdAndSenderId(Long messageId, String sender);
    Page<Chat> findAllByRoomOrderByCreateAtAsc(Room room, Pageable pageable);
}
