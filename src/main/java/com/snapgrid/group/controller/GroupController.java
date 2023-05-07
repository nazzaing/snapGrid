package com.snapgrid.group.controller;

import com.snapgrid.group.domain.Group;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.dto.groupAndMemberDto;
import com.snapgrid.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GroupController {


    private final GroupService groupService;



    // 그룹 목록
    @GetMapping("/groups")
    @ResponseBody
    public List<Group> groupList(@ModelAttribute("searchForm") GroupDto.searchRequest searchForm){
        //groupService.findGroupListPaging(searchForm);

        return groupService.findGroupListPaging(searchForm);


    }

    // 그룹 생성 폼
    @GetMapping("/group")
    public String groupForm(Model model) {
        GroupDto.createRequest createRequest = new GroupDto.createRequest();
        model.addAttribute("createForm", createRequest);
        return "group/create_group";
    }


    // 그룹 생성
    @PostMapping("/group")
    public String createGroup(@ModelAttribute  @Valid GroupDto.createRequest dto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error(bindingResult.toString());
            return "group/create_group";
        }

        /**
         *
         *  TODO 권한 검증
         *
         *  ~~~~~
         *
         *  끝
         */

        dto.setCreatorId(getAuth());

        groupService.createGroup(dto);

        return "redirect:/";

    }



    // 그룹 수정 폼
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

        model.addAttribute("group_form", findGroup);


        return "group/view_group";
    }


    // 그룹 수정
    @PostMapping("/group/{groupId}")
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
    public String joinGroup(@PathVariable Long groupId){

        /**
         *
         *  TODO 권한검증
         */

        Long userId = getAuth();

        groupAndMemberDto.createRequest requestDto = new groupAndMemberDto.createRequest(groupId,userId,LocalDateTime.now());


        groupService.joinGroup(requestDto);


        return "redirect:/";
    }


    // 그룹 탈퇴
    @PostMapping("/group/exit/{groupId}")
    public String exitGroup(@PathVariable Long groupId){

        /**
         *
         *  TODO 권한검증
         */
        Long userId = getAuth();
        groupAndMemberDto.deleteRequest requestDto = new groupAndMemberDto.deleteRequest(groupId,userId);

        groupService.exitGroup(requestDto);

        return "redirect:/";
    }







    // 권한 검증 로직
    public Long getAuth(){

        // TODO 임시...
        return Long.valueOf((long) ((Math.random()*30)));
    }






}
