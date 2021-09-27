package com.example.test.controller;

import com.example.test.model.testModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPostController {
    @GetMapping("/post")
    public testModel getPost(testModel testmodel){
        return testmodel;
    }

    @PostMapping("/postMapping")
    public testModel postMapping(testModel testmodel){
        return testmodel;
    }
}


