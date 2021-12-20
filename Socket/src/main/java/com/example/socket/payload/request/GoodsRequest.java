package com.example.socket.payload.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsRequest {
    private String title;
    private LocalDate untilDate;
    private Long price;
    private String info;
}
