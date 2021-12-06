package com.example.practice.Entity.member;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPassword(String password);
}
