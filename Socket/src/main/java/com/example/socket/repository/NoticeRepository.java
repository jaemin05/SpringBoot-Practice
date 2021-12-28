package com.example.socket.repository;

import com.example.socket.entity.chat.Notice;
import com.example.socket.entity.chat.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findALlByRoomAndTargetDateAfter(Room room, LocalDate date);
}
