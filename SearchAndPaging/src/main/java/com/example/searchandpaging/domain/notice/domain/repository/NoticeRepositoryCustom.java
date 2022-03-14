package com.example.searchandpaging.domain.notice.domain.repository;

import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse.NoticeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepositoryCustom {

    List<NoticeResponse> searchNoticeList(String keyword, Pageable pageable);
}
