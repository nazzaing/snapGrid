package com.snapgrid.member.service;

import com.snapgrid.member.dto.MemberDto;

public interface MemberService {
    MemberDto.Response join(MemberDto.Request request);
}
