package com.snapgrid.service;

import com.snapgrid.member.domain.Member;
import com.snapgrid.member.dto.MemberDto;
import com.snapgrid.member.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 및 조회 테스트")
    void joinAndSearch() {
        //given
        Member member1 = Member.builder()
                .userId("member1")
                .password("1234")
                .email("test@naver1.com")
                .userName("nick")
                .build();
        Member member2 = Member.builder()
                .userId("member2")
                .password("1234")
                .email("test@naver2.com")
                .userName("nick")
                .build();

        MemberDto.Request request = new MemberDto.Request();
        request.setUserId("member1");
        request.setPassword("1234");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        assertEquals(2L, memberRepository.findAll().size());
        assertEquals(1L, memberRepository.findByUserIdAndPasswordOrderByUserNameDesc(request.getUserId(), request.getPassword()).size());
    }

}