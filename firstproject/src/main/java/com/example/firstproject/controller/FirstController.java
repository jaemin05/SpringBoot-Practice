package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String hi(Model model) { //template 변수를 이용하기 위해서 Model 사용
        model.addAttribute("username", "재민");
        return "greetings"; //templates/greetings.mustache -> 브라우저 전송
    }

    @GetMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("username", "재민");
        return "goodbye";
    }

}


