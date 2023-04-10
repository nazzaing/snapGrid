package com.snapgrid.service;

import com.snapgrid.domain.member.Member;
import com.snapgrid.dto.member.MemberDto;
import com.snapgrid.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public MemberDto.Response join(MemberDto.Request request) {

        Member member = Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .build();
        memberRepository.save(member);

        return modelMapper.map(member, MemberDto.Response.class);
    }

}
