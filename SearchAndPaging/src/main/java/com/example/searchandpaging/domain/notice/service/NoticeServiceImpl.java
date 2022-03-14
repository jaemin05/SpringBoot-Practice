package com.example.searchandpaging.domain.notice.service;

import com.example.searchandpaging.domain.notice.domain.Notice;
import com.example.searchandpaging.domain.notice.domain.repository.NoticeRepository;
import com.example.searchandpaging.domain.notice.exception.NoticeNotFoundException;
import com.example.searchandpaging.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void createNotice(CreateNoticeRequest createNoticeRequest) {
        noticeRepository.save(
                Notice.builder()
                        .title(createNoticeRequest.getTitle())
                        .content(createNoticeRequest.getContent())
                        .build());
    }

    @Transactional
    public void deleteNotice(Long noticeId) {

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        noticeRepository.delete(notice);
    }

    @Transactional(readOnly = true)
    public QueryNoticeResponse searchNoticeList(String keyword, Pageable pageable) {

        List<NoticeResponse> noticeList = noticeRepository
                .searchNoticeList(keyword, pageable);

        return new QueryNoticeResponse(noticeList);
    }
}
