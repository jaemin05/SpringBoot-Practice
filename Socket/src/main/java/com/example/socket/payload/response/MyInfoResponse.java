package com.example.socket.payload.response;

import com.example.socket.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyInfoResponse {
    private String name;
    private String id;
    private String profile;
    private LocalDate birth;
    private Gender gender;
    private String info;

    private String address;
    private String addressCode;
    private String keyAddress;
    private String detailAddress;
}
