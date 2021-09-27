package com.example.test.controller;

import com.example.test.model.testModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestGetController {
    @GetMapping("/api")
    public String api(testModel testmodel) {
        return testmodel.getId() + " " + testmodel.getPassword();
    }

    @GetMapping("/getParam")
    public testModel getParam(testModel testmodel){
        return testmodel;
    }
}
