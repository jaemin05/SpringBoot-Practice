package com.example.socket.repository;

import com.example.socket.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndUserContaining(String roomId, String UserPk);

    boolean existsById(String id);

    Optional<Member> findById(String id);
}
