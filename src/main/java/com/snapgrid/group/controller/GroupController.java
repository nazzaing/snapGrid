package com.snapgrid.group.controller;

import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GroupController {


    private final GroupService groupService;



    // 그룹 생성 폼
    @GetMapping("/group")
    public String groupForm(Model model) {
        GroupDto.createRequest createRequest = new GroupDto.createRequest();
        model.addAttribute("createForm", createRequest);
        return "group/create_group";
    }


    // 그룹 생성
    @PostMapping("/group")
    public String createGroup(@ModelAttribute  @Valid GroupDto.createRequest dto, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            log.error(bindingResult.toString());
            return "group/create_group";
        }

        /**
         *
         *  TODO Member ID 가져오는 로직(session,jwt etc...) , 권한 검증
         *
         *  ~~~~~
         *
         *  끝
         */


        groupService.createGroup(dto);

        return "redirect:/";

    }


    @GetMapping("/group/{groupId}")
    public String viewGroup(@PathVariable Long groupId, Model model){

        /**
         * TODO 권한 검증
         *
         * ~~~~~~~~~
         *
         * 권한 검증 끝
         */


        GroupDto.Response findGroup = groupService.findGroup(groupId);

        model.addAttribute(findGroup);


        return "group/view_group";
    }


    @PatchMapping("/group/{groupId}")
    public String modifyGroup(@PathVariable Long groupId, @ModelAttribute @Valid GroupDto.modifyRequest dto, BindingResult bindingResult, Model model) {
        /**
         * TODO 권한 검증
         *
         * ~~~~~~~~~
         *
         * 권한 검증 끝
         */


        //dto.setUpdaterId();


        dto.setGroupId(groupId);

        if(bindingResult.hasErrors()){
            return "group/view_group";
        }


        groupService.modifyGroup(dto);

        return "redirect:/";
    }


    // 그룹 가입
    @PostMapping("/group/join/{groupId}")
    public String joinMember(@PathVariable Long groupId){

        /**
         *
         *  TODO 권한검증, 가입 가능 여부 체크(총 인원수, 그룹 공개비공개 여부 등 추가 예정?)
         */



        return "redirect:/";
    }






}
