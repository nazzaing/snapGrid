package com.snapgrid.member.repository.member;

import com.snapgrid.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>,
        MemberRepositoryCustom {
}
