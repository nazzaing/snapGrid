package com.snapgrid.group.repository;

import com.snapgrid.group.domain.GroupAndMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GroupAndMemberRepository {

    private final EntityManager em;


    public void save(GroupAndMember groupAndMember){
        em.persist(groupAndMember);
    }


}
