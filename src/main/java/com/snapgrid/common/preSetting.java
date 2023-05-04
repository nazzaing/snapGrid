package com.snapgrid.common;


import com.snapgrid.group.domain.Group;
import com.snapgrid.group.repository.GroupAndMemberRepository;
import com.snapgrid.group.repository.GroupRepository;
import com.snapgrid.member.domain.Member;
import com.snapgrid.member.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 *
 * 개발용 사전 세팅...
 *
 */

@Component
@RequiredArgsConstructor
public class preSetting {



    private final InitData initData;

    @PostConstruct
    public void preSetting() {
        initData.initMember();
        initData.initGroup();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitData{

        private final MemberRepository memberRepository;

        private final GroupRepository groupRepository;

        private final GroupAndMemberRepository groupAndMemberRepository;


        public void initMember(){
            for(int i=0; i<30; i++) {
                Member build = Member.builder()
                        .userId("아이디" + i)
                        .userName("이름" + i)
                        .password(UUID.randomUUID().toString())
                        .email("email@email.com" + i)
                        .phone("010-" + (Math.random() * 1000) + "-" + (Math.random() * 1000)).build();
                memberRepository.save(build);
            }
        }


        public void initGroup(){
            for(int i=0; i<30; i++) {
                Group group = Group.builder()
                        .groupName("이름" + i)
                        .createDate(LocalDateTime.now())
                        .address("지역" + i)
                        .category("카테고리" + i)
                        .creatorId(Long.valueOf(i))
                        .introduction("소개글" + i)
                        .updaterId(Long.valueOf(i)).build();

                groupRepository.save(group);


            }

        }




    }
}
