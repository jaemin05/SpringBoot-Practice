package com.example.searchandpaging.domain.notice.domain.repository;

import com.example.searchandpaging.domain.notice.presentation.dto.response.QQueryNoticeResponse_NoticeResponse;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse.NoticeResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.searchandpaging.domain.notice.domain.QNotice.notice;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeResponse> searchNoticeList(String keyword, Pageable pageable) {
        return jpaQueryFactory
                .select(new QQueryNoticeResponse_NoticeResponse(
                        notice.title,
                        notice.content,
                        notice.createAt
                ))
                .from(notice)
                .where(notice.title.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
