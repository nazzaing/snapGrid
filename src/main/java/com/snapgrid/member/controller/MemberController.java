package com.snapgrid.member.controller;

import com.snapgrid.member.dto.MemberDto;
import com.snapgrid.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping(path = "/member/join")
    public String joinForm() {
        return "main/main";
    }

    @PostMapping(path = "/member/join")
    public String joinProc(MemberDto.Request request) {
        MemberDto.Response joinResult = memberService.join(request);
        return "redirect:/";
    }
}
