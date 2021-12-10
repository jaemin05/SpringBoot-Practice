package com.example.fileupload.controller;

import com.example.fileupload.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    @PostMapping("/images")
    public String images(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadImages(multipartFile, "static");
    }

}
