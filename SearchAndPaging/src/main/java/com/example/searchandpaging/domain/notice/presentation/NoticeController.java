package com.example.searchandpaging.domain.notice.presentation;

import com.example.searchandpaging.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.example.searchandpaging.domain.notice.presentation.dto.response.QueryNoticeResponse;
import com.example.searchandpaging.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNotice(@RequestBody @Valid CreateNoticeRequest createNoticeRequest) {

        noticeService.createNotice(createNoticeRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{notice-id}")
    public void deleteNotice(@PathVariable(name = "notice-id") Long noticeId) {

        noticeService.deleteNotice(noticeId);
    }

    @GetMapping
    public QueryNoticeResponse searchNoticeList(@RequestParam String keyword,
                                                @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return noticeService.searchNoticeList(keyword, pageable);
    }
}
