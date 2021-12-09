package com.example.fileupload.controller;

import com.example.fileupload.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestParam MultipartFile file) {
        return s3Uploader.uploadImage(file);
    }

}
