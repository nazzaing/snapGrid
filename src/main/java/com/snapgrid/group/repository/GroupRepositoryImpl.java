package com.snapgrid.group.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.snapgrid.group.domain.QGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    QGroup group = QGroup.group;

}
