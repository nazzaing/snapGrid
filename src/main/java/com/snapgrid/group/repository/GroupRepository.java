package com.snapgrid.group.repository;

import com.snapgrid.group.domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {


    Page<Group> findAllBy(Pageable pageable);

}
