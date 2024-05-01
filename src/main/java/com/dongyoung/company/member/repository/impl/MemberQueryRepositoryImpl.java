package com.dongyoung.company.member.repository.impl;

import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.entity.QMember;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.SearchCondition;
import com.dongyoung.company.member.model.mapper.MemberMapper;
import com.dongyoung.company.member.repository.MemberQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dongyoung.company.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final MemberMapper memberMapper;

    @Override
    public Page<FindResponseMemberListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        List<Member> list = queryFactory.selectFrom(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(search(searchCondition.name()))
                .orderBy(member.memberId.desc())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(Wildcard.count).from(member);
        return PageableExecutionUtils.getPage(list.stream().map(memberMapper::toMemberListModel).collect(Collectors.toList()), pageable, count::fetchOne);
    }

    private BooleanExpression search(String searchVal) {
        return searchVal != null ? member.addName.name.contains(searchVal) : null;
    }
}
