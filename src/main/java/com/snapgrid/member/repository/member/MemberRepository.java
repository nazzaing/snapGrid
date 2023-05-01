package com.snapgrid.member.repository.member;

import com.snapgrid.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String>,
        MemberRepositoryCustom {
    List<Member> findByUserIdAndPasswordOrderByUserNameDesc(String userId, String password);
}
