package com.snapgrid.group.service;


import com.snapgrid.group.domain.Group;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.repository.GroupRepository;
import com.snapgrid.member.domain.Member;
import com.snapgrid.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    private final ModelMapper modelMapper;


    @Override
    public GroupDto.Response createGroup(GroupDto.Request request) {
        Group group = Group.builder()
                .groupName(request.getGroupName())
                .introduction(request.getIntroduction())
                .address(request.getAddress())
                .creatorId(request.getCreatorId())
                .createDate(request.getCreateDate())
                .category(request.getCategory())
                .build();


        groupRepository.save(group);

        return modelMapper.map(group, GroupDto.Response.class);
    }

    @Override
    public GroupDto.Response removeGroup(GroupDto.Request request) {
        return null;
    }

    @Override
    public GroupDto.Response modifyGroup(GroupDto.Request request) {
        return null;
    }

    @Override
    public GroupDto.Response joinGroup(GroupDto.Request request) {
        return null;
    }

    @Override
    public GroupDto.Response exitGroup(GroupDto.Request request) {
        return null;
    }
}
