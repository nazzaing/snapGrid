package com.snapgrid.group.domain;

import com.snapgrid.group.dto.GroupDto;
import com.snapgrid.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName; // 모임 이름


    private String introduction; // 모임 소개글

    private String address; // 모임 장소

    //todo 테이블 따로 생성해서 사용?
    private String category; // 모임 카테고리


    @Column(name = "creator_id")
    private Long creatorId; // 생성자 ID


    @Column(name = "updater_id")
    private Long updaterId; // 수정자 ID

    @Column(name = "create_date")
    private LocalDateTime createDate; // 생성일


    @Column(name = "update_date")
    private LocalDateTime updateDate; // 수정일


    /////////////////


    // mapping
    @OneToMany(mappedBy = "groupId")
    private List<GroupAndMember> groupAndMember = new ArrayList<>();



    ////////////////

    public void modifyInfo(String groupName, String introduction, String address, String category, Long updaterId, LocalDateTime updateDate){

    }

    @Builder
    public Group(String groupName, String introduction, String address, String category, Long creatorId, Long updaterId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.groupName = groupName;
        this.introduction = introduction;
        this.address = address;
        this.category = category;
        this.creatorId = creatorId;
        this.updaterId = updaterId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    protected Group() {

    }
}
