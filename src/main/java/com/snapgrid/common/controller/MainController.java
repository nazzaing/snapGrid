package com.snapgrid.common.controller;

import com.snapgrid.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("memberForm", new MemberDto.Request());
        return "main/main";
    }
}
