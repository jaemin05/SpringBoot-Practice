package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import payload.EmailRequest;
import payload.EmailVerifiedRequest;
import payload.MemberRequest;
import service.member.MemberService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class EmailController {
    private final MemberService memberService;

    @PostMapping("/email")
    public void sendEmail(@Valid @RequestBody EmailRequest request){
        memberService.sendEmail(request);
    }

    @PostMapping("/email")
    public void verifyAccount(@Valid @RequestBody EmailVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody MemberRequest request) {
        memberService.signup(request);
    }

}
