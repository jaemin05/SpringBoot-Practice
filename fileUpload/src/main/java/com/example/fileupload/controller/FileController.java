package com.example.fileupload.controller;

import com.example.fileupload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "download")
    public ResponseEntity<Resource> serveFile(@RequestParam(value = "filename") String filename) {
        Resource file = fileService.loadAsResource(filename);
        return ResponseEntity.ok().header( //CONTENT_DISPOSITION : 웹페이지에서 HTTP 프로토콜이 응답하는 데이터를 어떻게 표시하는지 알려주는 Header
                //attachment : 사용자가 로컬에서 다운로드할 수 있도록 해준다
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping(value = "deleteAll")
    public ResponseEntity<String> deleteAll() {
        /* 파일을 하나만 삭제하려면 StorageService Interface 에 추상 메소드 delete() 를 추가하고
        FileSystemStorageService.java 에 Override 해서 구현하고 사용하면 됩니다. */

        fileService.deleteAll();
        return new ResponseEntity<>("Success delete", HttpStatus.OK);
    }
}
