package com.snapgrid.repository.member;

import com.snapgrid.domain.member.Member;
import com.snapgrid.dto.member.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    // QueryDSLSample
    List<Member> findByNamePasswordOrderByNameDesc(MemberDto.Request request);
}