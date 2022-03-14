package com.example.searchandpaging.domain.notice.service;

import com.example.searchandpaging.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    public void createNotice(CreateNoticeRequest createNoticeRequest);

    public void deleteNotice(Long noticeId);

    public QueryNoticeResponse searchNoticeList(String keyword, Pageable pageable);

}
