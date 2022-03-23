package com.example.fcmpractice.domain.notice.service;

import com.example.fcmpractice.domain.notice.presentation.dto.NoticeRequest;

public interface NoticeService {
    void createNotice(NoticeRequest createNoticeRequest);

    void deleteNotice(Long noticeId);
}
