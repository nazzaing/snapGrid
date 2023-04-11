package com.snapgrid.member.repository.member;

import com.snapgrid.member.domain.Member;
import com.snapgrid.member.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    // QueryDSLSample
    List<Member> findByNamePasswordOrderByNameDesc(MemberDto.Request request);
}