package com.example.fileupload.service;

import com.example.fileupload.exception.FileEmptyException;
import com.example.fileupload.exception.FileNotFoundException;
import com.example.fileupload.exception.FileNotStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import java.io.InputStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.upload.location}")
    private String uploadPath;

    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException ignored) {
            throw FileNotFoundException.EXCEPTION;
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw FileEmptyException.Exception;
            }
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }
            try (InputStream inputStream = file.getInputStream()) {
                //StandardCopyOption.REPLACE_EXISTING : 파일이 이미 존재할 경우 덮어쓰기
                Files.copy(inputStream, root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw FileNotStoreException.Exception;
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            Path root = Paths.get(uploadPath);
            //파일 목록 가져오기
            return Files.walk(root, 1)
                    .filter(path -> !path.equals(root));
        } catch (IOException e) {
            throw FileNotStoreException.Exception;
        }
    }

    @Override
    public Path load(String filename) {
        return Paths.get(uploadPath).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw FileNotFoundException.EXCEPTION;
            }
        } catch (MalformedURLException e) {
            throw FileNotFoundException.EXCEPTION;
        }
    }

    @Override
    public void deleteAll() {
        //전체 파일 삭제
        FileSystemUtils.deleteRecursively(Paths.get(uploadPath).toFile());
    }
}
