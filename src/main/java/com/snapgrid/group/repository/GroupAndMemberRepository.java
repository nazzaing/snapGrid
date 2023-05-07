package com.snapgrid.group.repository;

import com.snapgrid.group.domain.Group;
import com.snapgrid.group.domain.GroupAndMember;
import com.snapgrid.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface GroupAndMemberRepository extends JpaRepository<GroupAndMember, Long> {

    Long countByGroupAndMember(@Param("group_id")Group group, @Param("member_id")Member member);

    GroupAndMember findByGroupAndMember(@Param("group_id")Group group, @Param("member_id")Member member);



}
