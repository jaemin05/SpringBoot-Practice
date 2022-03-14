package com.example.searchandpaging.domain.notice.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNoticeResponse {

    private final List<NoticeResponse> noticeResponseList;

    @Getter
    public static class NoticeResponse {
        private final String title;
        private final String content;
        private final LocalDateTime createAt;

        @QueryProjection
        public NoticeResponse(String title, String content, LocalDateTime createAt) {
            this.title = title;
            this.content = content;
            this.createAt = createAt;
        }
    }
}
