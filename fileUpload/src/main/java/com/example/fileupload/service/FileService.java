package com.example.fileupload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

//보통 파일이름을 오리지널 그대로 올리는 경우가 있는데 그렇게 해선 안된다.
// 이름이 겹치면 올라가지 않을뿐더라 나중에 보안에 문제가 생길 수 있다.
// 원래 파일이름을 통신을 통해 보내서 DB에 저장하고 파일이름 설계에 의한 이름 + 타임스태프로 해주는 경우가 많으니 참고바란다.
// 우선 난 타임스탬프 + _파일이름으로 해놓을 예정이다. 물론 Response에서 오리지널 이름과 변환 이름 둘 다 반환한다

public interface FileService {
    void init();
    void store(MultipartFile file);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
