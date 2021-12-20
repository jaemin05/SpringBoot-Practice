package com.example.socket.entity;

import com.example.socket.entity.chat.Room;
import com.example.socket.payload.request.GoodsRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Room room;

    private String title;

    private LocalDate utilDate;

    private Long price;

    private String info;

    private String OwnerId;

    private String image;

    public Goods updateGoods(GoodsRequest request) {
        this.title = request.getTitle();
        this.utilDate = request.getUntilDate();
        this.price = request.getPrice();
        this.info = request.getInfo();
        return this;
    }

    public Goods addProfile(String image) {
        this.image = image;
        return this;
    }


}
