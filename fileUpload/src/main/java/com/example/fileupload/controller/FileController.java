package com.example.fileupload.controller;

import com.example.fileupload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "upload")
    public ResponseEntity<String> upload(MultipartFile file) throws IllegalStateException, IOException {
        fileService.store(file);
        return new ResponseEntity<>("Success File Upload", HttpStatus.OK);
    }


}
