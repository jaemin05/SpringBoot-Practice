package com.example.socket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "memebr")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifyId;

    private String id;

    private LocalDate birth;

    private Gender gender;

    private String name;

    private String password;

    private String picture;

    private String info;

    private String address;

    private String addressCode;

    private String keyAddress;

    private String detailAddress;

    private LoginType loginType;

    private String phone;

    @CreatedDate
    private LocalDate created;

    private Role role;

    private String salt;



}
