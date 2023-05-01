package com.snapgrid.member.service;

import com.snapgrid.member.domain.Member;
import com.snapgrid.member.dto.MemberDto;
import com.snapgrid.member.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MemberDto.Response join(MemberDto.Request request) {
        Member member = Member.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .userName(request.getUserName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        memberRepository.save(member);

        return modelMapper.map(member, MemberDto.Response.class);
    }

}
