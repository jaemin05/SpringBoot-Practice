package com.example.searchandpaging.domain.notice.service;

import com.example.searchandpaging.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    void createNotice(CreateNoticeRequest createNoticeRequest);

    void deleteNotice(Long noticeId);

    QueryNoticeResponse searchNoticeList(String keyword, Pageable pageable);
}
