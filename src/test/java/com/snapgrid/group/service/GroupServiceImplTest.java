package com.snapgrid.group.service;

import com.snapgrid.group.domain.Group;
import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.group.repository.GroupRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupServiceImplTest {



    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupServiceImpl groupService;



    @Test
    void 모임생성_서비스테스트(){

        Group group = Group.builder()
                .groupName("그룹이름1")
                .introduction("소개글입니다")
                .address("서울시 어쩌구")
                .creatorId(1L)
                .createDate(LocalDateTime.now())
                .category("취미")
                .build();


        ModelMapper modelMapper = new ModelMapper();


        GroupDto.Request request = modelMapper.map(group, GroupDto.Request.class);

        GroupDto.Response response = groupService.createGroup(request);

        Assertions.assertThat(response.getGroupId()).isNotNull();

    }





}