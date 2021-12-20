package com.example.socket.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserInfoRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean name;
    private boolean phone;
    private boolean birth;
    private boolean gender;
    private boolean address;
}
