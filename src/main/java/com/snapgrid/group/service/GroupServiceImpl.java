package com.snapgrid.group.service;


import com.snapgrid.group.domain.Group;
import com.snapgrid.group.domain.GroupAndMember;
import com.snapgrid.group.domain.GroupRole;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.repository.GroupAndMemberRepository;
import com.snapgrid.group.repository.GroupRepository;
import com.snapgrid.member.domain.Member;
import com.snapgrid.member.dto.MemberDto;
import com.snapgrid.member.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    private final MemberRepository memberRepository;

    private final GroupAndMemberRepository groupAndMemberRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public GroupDto.Response createGroup(GroupDto.createRequest createDto) {


        Member member = memberRepository.findById(createDto.getCreatorId()).orElseThrow(() -> new NullPointerException("find member Error!"));
        memberRepository.save(member);


        // DTO -> Group
        Group group = createDtoToGroup(createDto);

        groupRepository.save(group);

        GroupAndMember groupAndMemberEntity = GroupAndMember.createGroupAndMember(group,member,GroupRole.ADM, createDto.getCreateDate());

        groupAndMemberRepository.save(groupAndMemberEntity);



        return modelMapper.map(group, GroupDto.Response.class);
    }



    @Override
    public GroupDto.Response removeGroup(GroupDto.Request request) {
        return null;
    }


    @Override
    public GroupDto.Response modifyGroup(GroupDto.modifyRequest dto) {

        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new NullPointerException("group find Error!"));

        // 수정
        group.modifyInfo(dto.getGroupName(), dto.getIntroduction(), dto.getAddress(), dto.getCategory(), dto.getUpdaterId(), dto.getUpdateDate());

        return groupToResponseDto(group);
    }

    @Override
    public GroupDto.Response joinGroup(GroupDto.Request request) {
        return null;
    }

    @Override
    public GroupDto.Response exitGroup(GroupDto.Request request) {
        return null;
    }

    @Override
    public GroupDto.Response findGroup(Long groupId) {

        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NullPointerException());

        return groupToResponseDto(group);
    }

    private GroupDto.Response groupToResponseDto(Group group) {
        return GroupDto.Response.builder()
                .groupId(group.getId())
                .groupName(group.getGroupName())
                .introduction(group.getIntroduction())
                .address(group.getAddress())
                .category(group.getCategory())
                .createDate(group.getCreateDate())
                .updateDate(group.getUpdateDate())
                .creatorId(group.getCreatorId()).build();
    }

    private Group createDtoToGroup(GroupDto.createRequest createDto) {
        Group grpEntity = Group.builder()
                .groupName(createDto.getGroupName())
                .introduction(createDto.getIntroduction())
                .address(createDto.getAddress())
                .creatorId(createDto.getCreatorId())
                .createDate(createDto.getCreateDate())
                .category(createDto.getCategory())
                .build();
        Group group = groupRepository.save(grpEntity);
        return group;
    }
}
