package com.snapgrid.group.service;


import com.snapgrid.group.domain.Group;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.dto.groupAndMemberDto;

import java.util.List;

public interface GroupService {

    public GroupDto.Response createGroup(GroupDto.createRequest dto); // 모임 생성

    public GroupDto.Response removeGroup(GroupDto.Request request); // 모임 삭제

    public GroupDto.Response modifyGroup(GroupDto.modifyRequest dto); // 모임정보 수정


    public GroupDto.Response joinGroup(groupAndMemberDto.createRequest dto); // 모임 가입

    public GroupDto.Response exitGroup(groupAndMemberDto.deleteRequest dto); // 모임 탈퇴

    public GroupDto.Response findGroup(Long groupId); // 모임 정보 조회

    public List<Group> findGroupListPaging(GroupDto.searchRequest searchForm);
}
