package com.snapgrid.member.repository.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.snapgrid.member.domain.Member;
import com.snapgrid.member.domain.QMember;
import com.snapgrid.member.dto.MemberDto;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    @Override
    public List<Member> findByNamePasswordOrderByNameDesc(MemberDto.Request request) {

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(request.getName())) {
            builder.and(member.name.eq(request.getName()));
        }
        if (StringUtils.isNotBlank(request.getPassword())) {
            builder.and(member.password.eq(request.getPassword()));
        }

        return queryFactory.select(member)
                .from(member)
                .where(builder)
                .orderBy(member.name.desc())
                .fetch();
    }
}
