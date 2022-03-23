package com.example.fcmpractice.domain.notice.presentation;

import com.example.fcmpractice.domain.notice.presentation.dto.NoticeRequest;
import com.example.fcmpractice.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNotice(@RequestBody @Valid NoticeRequest noticeRequest) {
        noticeService.createNotice(noticeRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/notice-id")
    public void deleteNotice(@PathVariable(name = "notice-id") Long noticeId) {
        noticeService.deleteNotice(noticeId);
    }
}
