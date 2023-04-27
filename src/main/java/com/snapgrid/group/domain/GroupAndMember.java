package com.snapgrid.group.domain;


import com.snapgrid.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "group_member")
public class GroupAndMember {


    @Id
    @GeneratedValue
    @Column(name = "group_member_id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group groups;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member  memberId;


    @Enumerated(EnumType.STRING)
    private GroupRole role; // 모임내 사용자 권한


    @Column(name = "join_date")
    private LocalDateTime joinDate; // 모임 가입일

}
