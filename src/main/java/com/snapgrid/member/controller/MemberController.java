package com.snapgrid.member.controller;

import com.snapgrid.common.dto.ResponseDto;
import com.snapgrid.member.dto.MemberDto;
import com.snapgrid.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping(path = "/member/join")
    public String joinForm() {
        return "main/main";
    }

    @PostMapping(path = "/member/join")
    @ResponseBody
    public ResponseDto<MemberDto.Response> joinProc(MemberDto.Request request) {
        MemberDto.Response joinResult = memberService.join(request);
        return new ResponseDto<MemberDto.Response>(HttpStatus.OK.value(), joinResult);
    }
}
