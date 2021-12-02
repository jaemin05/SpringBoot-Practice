package controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import payload.EmailRequest;
import payload.EmailVerifiedRequest;
import service.member.MemberService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class EmailController {
    private final MemberService memberService;

    @GetMapping("/email")
    public void sendEmail(@Valid @RequestBody EmailRequest request){
        memberService.sendEmail(request);
    }

    @PostMapping
    public void varifyAccount(@Valid @RequestBody EmailVerifiedRequest request) {
        memberService.verifyAccount(request);
    }
}
