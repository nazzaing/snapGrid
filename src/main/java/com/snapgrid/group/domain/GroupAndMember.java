package com.snapgrid.group.domain;


import com.snapgrid.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "group_member")
public class GroupAndMember {


    @Id
    @GeneratedValue
    @Column(name = "group_member_id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group groupId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member  memberId;


    @Enumerated(EnumType.STRING)
    private GroupRole role; // 모임내 사용자 권한


    @Column(name = "join_date")
    private LocalDateTime joinDate; // 모임 가입일


    protected GroupAndMember() {
    }

    @Builder
    public GroupAndMember(Group groupId, Member memberId, GroupRole role, LocalDateTime joinDate) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.role = role;
        this.joinDate = joinDate;
    }
    
    
    public static GroupAndMember createGroupAndMember(Group groupId, Member memberId, GroupRole role, LocalDateTime joinDate){
        GroupAndMember groupAndMember = new GroupAndMember();
        groupAndMember.addGroups(groupId);
        groupAndMember.addMember(memberId);
        groupAndMember.setRole(role);
        groupAndMember.setJoinDate(joinDate);

        return groupAndMember;
    }


    public void addGroups(Group group){
        group.getGroupAndMember().add(this);
        this.groupId = group;
    }
    
    public void addMember(Member member){
        // TODO MemberEntity 수정 할시 아래 주석 해제
        //member.getGroupAndMember().add(this);
        this.memberId = member;
        
    }
}
