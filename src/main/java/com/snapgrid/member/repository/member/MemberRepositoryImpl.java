package com.snapgrid.member.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.snapgrid.member.domain.QMember;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    /*@Override
    public List<Member> findByNamePasswordOrderByNameDesc(MemberDto.Request request) {

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(request.getUserName())) {
            builder.and(member.userName.eq(request.getUserName()));
        }
        if (StringUtils.isNotBlank(request.getPassword())) {
            builder.and(member.password.eq(request.getPassword()));
        }

        return queryFactory.select(member)
                .from(member)
                .where(builder)
                .orderBy(member.userName.desc())
                .fetch();
    }*/
}
