package com.snapgrid.group.service;


import com.snapgrid.group.domain.Group;
import com.snapgrid.group.domain.GroupAndMember;
import com.snapgrid.group.domain.GroupRole;
import com.snapgrid.group.domain.GroupStatus;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.dto.groupAndMemberDto;
import com.snapgrid.group.repository.GroupAndMemberRepository;
import com.snapgrid.group.repository.GroupRepository;
import com.snapgrid.member.domain.Member;
import com.snapgrid.member.repository.member.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    private final MemberRepository memberRepository;

    private final GroupAndMemberRepository groupAndMemberRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Group> findGroupListPaging(GroupDto.searchRequest searchForm) {
        // TODO 동적 쿼리 작성 할것.

        PageRequest pageRequest = PageRequest.of(searchForm.getPageNum(), searchForm.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Group> pageAll = groupRepository.findAllBy(pageRequest);
        List<Group> content = pageAll.getContent();
        return content;


    }

    @Override
    @Transactional
    public GroupDto.Response createGroup(GroupDto.createRequest createDto) {


        Member member = memberRepository.findById(createDto.getCreatorId()).orElseThrow(() -> new IllegalArgumentException ("find member Error!"));
        memberRepository.save(member);


        // DTO -> Group
        Group group = createDtoToGroup(createDto);
        groupRepository.save(group);


        GroupAndMember groupAndMemberEntity = GroupAndMember.createGroupAndMember(group,member,GroupRole.ADM);
        groupAndMemberRepository.save(groupAndMemberEntity);



        return modelMapper.map(group, GroupDto.Response.class);
    }



    @Override
    public GroupDto.Response removeGroup(GroupDto.Request request) {
        return null;
    }


    @Override
    @Transactional
    public GroupDto.Response modifyGroup(GroupDto.modifyRequest dto) {

        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new IllegalArgumentException("group find Error!"));

        // 수정
        group.modifyInfo(dto.getGroupName(), dto.getIntroduction(), dto.getAddress(), dto.getCategory(), dto.getUpdaterId());

        return groupToResponseDto(group);
    }



    @Override
    @Transactional
    public GroupDto.Response joinGroup(groupAndMemberDto.createRequest dto) {

        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("member find Error"));
        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new IllegalArgumentException("group find Error"));

        if(!group.getStatus().equals(GroupStatus.PUBLIC)){
          throw new RuntimeException("그룹이 가입을 받고 있지 않습니다.");
        }


        // 이미 가입했는지 체크
        Long existed = groupAndMemberRepository.countByGroupAndMember(group, member);
        if(existed > 0){
            throw new RuntimeException("이미 가입했습니다");
        }



        GroupAndMember groupAndMember = GroupAndMember.createGroupAndMember(group, member, GroupRole.NORMAL);
        GroupAndMember save = groupAndMemberRepository.save(groupAndMember);

        return null;
    }

    @Override
    public GroupDto.Response exitGroup(groupAndMemberDto.deleteRequest dto) {

        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("member find Error"));
        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new IllegalArgumentException("group find Error"));

        // 가입이 되어있는지 확인
        GroupAndMember groupAndMember = groupAndMemberRepository.findByGroupAndMember(group, member);

        if(groupAndMember == null){
            throw new RuntimeException("이미 탈퇴 했거나 가입이 되어있지 않았습니다.");
        }

        if (groupAndMember.getRole() == GroupRole.ADM){
            throw new RuntimeException("관리자 등급은 탈퇴가 불가능합니다.");
        }

        groupAndMemberRepository.delete(groupAndMember);

        return null;
    }

    @Override
    public GroupDto.Response findGroup(Long groupId) {

        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("멤버가 읍서요"));

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

        Group group = Group.createGroup(createDto.getGroupName(), createDto.getIntroduction(), createDto.getAddress(), createDto.getCategory(), createDto.getCreatorId());
        return group;
    }

}
