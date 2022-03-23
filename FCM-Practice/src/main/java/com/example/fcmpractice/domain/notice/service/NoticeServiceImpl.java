package com.example.fcmpractice.domain.notice.service;

import com.example.fcmpractice.domain.notice.domain.Notice;
import com.example.fcmpractice.domain.notice.domain.repository.NoticeRepository;
import com.example.fcmpractice.domain.notice.exception.InvalidUserException;
import com.example.fcmpractice.domain.notice.exception.NoticeNotFoundException;
import com.example.fcmpractice.domain.notice.presentation.dto.NoticeRequest;
import com.example.fcmpractice.domain.user.domain.User;
import com.example.fcmpractice.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final UserFacade userFacade;
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void createNotice(NoticeRequest createNoticeRequest) {
        User user = userFacade.getCurrentUser();

        noticeRepository.save(
                Notice.builder()
                        .title(createNoticeRequest.getTitle())
                        .content(createNoticeRequest.getContent())
                        .user(user)
                        .build()
        );
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeId) {

        User user = userFacade.getCurrentUser();
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (!notice.getUser().equals(user))
            throw InvalidUserException.EXCEPTION;

        noticeRepository.delete(notice);
    }
}
