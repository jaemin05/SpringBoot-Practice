package com.example.socket.repository;

import com.example.socket.entity.Member;
import com.example.socket.entity.chat.Room;
import com.example.socket.entity.chat.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findAllByRoomType(RoomType Type, Pageable pageable);

    Optional<Room> findByIdAndMembersContaining(String roomId, Member member);

    void deleteById(String roodId);

    Optional<Room> findByIdAndAdmin(String roomId, String adminId);

    boolean existsByIdAndMembersContaining(String roomId, Member member);

    boolean existsByIdAndAdmin(String roomId, String adminId);

    List<Room> findRoomsByMembersContaining(Member member);

    Optional<Room> findById(String Id);

}
