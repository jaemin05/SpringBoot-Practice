package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
       model.addAttribute("data", "HELLO");
       model.addAttribute("data2", "BYE");
       return "hello";
    }
}

