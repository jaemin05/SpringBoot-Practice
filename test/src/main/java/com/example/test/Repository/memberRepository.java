package com.example.test.Repository;

import com.example.test.Controller.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface memberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByusername(String username);
}
