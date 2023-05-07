package com.snapgrid.group.domain;


import com.snapgrid.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "group_member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"group_id", "member_id"})
        }
       )
public class GroupAndMember {


    @Id
    @GeneratedValue
    @Column(name = "group_member_id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member  member;


    @Enumerated(EnumType.STRING)
    private GroupRole role; // 모임내 사용자 권한


    @Column(name = "join_date")
    private LocalDateTime joinDate; // 모임 가입일



    protected GroupAndMember() {
    }

    @Builder
    public GroupAndMember(Group group, Member member, GroupRole role, LocalDateTime joinDate) {
        this.group = group;
        this.member = member;
        this.role = role;
        this.joinDate = joinDate;
    }
    
    
    public static GroupAndMember createGroupAndMember(Group group, Member member, GroupRole role){
        GroupAndMember groupAndMember = new GroupAndMember();
        groupAndMember.addGroups(group); // group entity 에 this 추가
        groupAndMember.addMember(member); // member entity 에 this 추가
        groupAndMember.setRole(role);
        groupAndMember.setJoinDate(LocalDateTime.now());


        return groupAndMember;
    }


    public void addGroups(Group group){
        group.getGroupAndMember().add(this);
        group.increaseMemberCount();
        this.group = group;



    }
    
    public void addMember(Member member){
        // TODO MemberEntity 수정 할시 아래 주석 해제
        //member.getGroupAndMember().add(this);
        this.member = member;
        
    }
}
