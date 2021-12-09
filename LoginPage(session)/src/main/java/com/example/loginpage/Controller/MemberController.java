package com.example.loginpage.Controller;

import com.example.loginpage.Dto.MemberDto;
import com.example.loginpage.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "/home/index";
    }

    @GetMapping("/member/signup")
    public String sighupForm(Model model) {
        model.addAttribute("member", new MemberDto());

        return "/member/signupForm";
    }

    @GetMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("member", new MemberDto());
        return "/home/index";
    }

    @PostMapping("/member/signup")
    public String signup(MemberDto memberDto) {
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login() {
        return "/member/loginForm";
    }
}
