package com.snapgrid.group.service;


import com.snapgrid.group.dto.GroupDto;

public interface GroupService {

    public GroupDto.Response createGroup(GroupDto.Request request); // 모임 생성

    public GroupDto.Response removeGroup(GroupDto.Request request); // 모임 삭제

    public GroupDto.Response modifyGroup(GroupDto.Request request); // 모임정보 수정


    public GroupDto.Response joinGroup(GroupDto.Request request); // 모임 가입

    public GroupDto.Response exitGroup(GroupDto.Request request); // 모임 탈퇴


}
