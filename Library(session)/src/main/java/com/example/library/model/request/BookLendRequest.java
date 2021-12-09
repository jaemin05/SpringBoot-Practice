package com.example.library.model.request;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class BookLendRequest {
    private List<Long> bookId;
    private Long memberId;
}
