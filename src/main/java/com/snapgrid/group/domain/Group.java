package com.snapgrid.group.domain;

import com.snapgrid.common.domain.BaseDateEntity;
import com.snapgrid.group.exception.MemberFullException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="groups")
//@DynamicUpdate
public class Group extends BaseDateEntity {
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



    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private GroupStatus status = GroupStatus.PUBLIC; // 그룹 상태 (공개, 비공개)


    private Long max_member = 100L; // 최대 가입자수

    private Long count_member = 0L; // 현재 가입자수


    /////////////////


    // mapping
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<GroupAndMember> groupAndMember = new ArrayList<>();



    ////////////////

    public void modifyInfo(String groupName, String introduction, String address, String category, Long updaterId){
        this.groupName = groupName;
        this.introduction = introduction;
        this.address = address;
        this.category = category;
        this.updaterId = updaterId;
    }

    @Builder
    public Group(String groupName, String introduction, String address , String category, Long creatorId, Long updaterId) {
        this.groupName = groupName;
        this.introduction = introduction;
        this.address = address;
        this.category = category;
        this.creatorId = creatorId;
        this.updaterId = updaterId;
    }
    
    public static Group createGroup(String groupName, String introduction, String address , String category, Long creatorId){
        Group group = new Group();
        group.groupName = groupName;
        group.introduction = introduction;
        group.address = address;
        group.category = category;
        group.creatorId = creatorId;
        group.max_member = 100L;
        group.count_member = 0L;
        
        return group;
    }

    protected Group() {

    }


    // 가입시 가입자수 +
    public void increaseMemberCount(){

        if( this.max_member < this.count_member+1){
            throw new MemberFullException("더 이상 가입할 수 없습니다.");
        }else{
            this.count_member += 1;
        }
    }


    // 탈퇴시 가입자수 -
    public void decreaseMemberCount(){

        if( this.count_member-1 < 0){
            throw new IllegalStateException("?");
        }else{
            this.count_member -= 1;
        }
    }

}
