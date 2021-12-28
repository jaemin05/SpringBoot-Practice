package com.example.socket.entity;

import com.example.socket.payload.response.MyInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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

    @ElementCollection
    private List<String> user = new ArrayList<>();


    public void phone(String phone){
        this.phone = phone;
    }

    public void updateMember(String name, LocalDate birth, String info){
        this.name = name;
        this.birth = birth;
        this.info = info;
    }

    public void editInfo(String info){
        this.info = info;
    }

    public MyInfoResponse toMyInfoResponse(Member member){
        return MyInfoResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .profile(member.getPicture())
                .birth(member.getBirth())
                .info(member.getInfo())
                .build();
    }

    public String updateProfile(String profileUrl){
        this.picture = profileUrl;
        return profileUrl;
    }

}
