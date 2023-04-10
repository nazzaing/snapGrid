package com.snapgrid.controller;

import com.snapgrid.dto.member.MemberDto;
import com.snapgrid.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(path = "/member/join")
    public String joinForm() {
        return "home";
    }

    @PostMapping(path = "/member/join")
    public String joinProc(MemberDto.Request request) {
        MemberDto.Response joinResult = memberService.join(request);
        return "redirect:/";
    }
}
