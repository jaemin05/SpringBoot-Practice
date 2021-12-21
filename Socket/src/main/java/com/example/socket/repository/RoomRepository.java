package com.example.socket.repository;

import com.example.socket.entity.Member;
import com.example.socket.entity.chat.Room;
import com.example.socket.entity.chat.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByAllType(RoomType roomType, Pageable pageable);

    Optional<Room> findByIdAndMembersContaining(String roomId, Member member);

    void deleteById(String roodId);

    
}
