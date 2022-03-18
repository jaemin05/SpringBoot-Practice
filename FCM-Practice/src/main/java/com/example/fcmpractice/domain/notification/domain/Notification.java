package com.example.fcmpractice.domain.notification.domain;

import com.example.fcmpractice.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20, nullable = false)
    private String title;

    @NotNull
    @Column(length = 200)
    private String content;

    @Builder
    public Notification(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
