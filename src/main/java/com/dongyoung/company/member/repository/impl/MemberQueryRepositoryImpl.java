package com.dongyoung.company.member.repository.impl;

import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.SearchCondition;
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

import static com.dongyoung.company.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FindResponseMemberListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        List<Member> list = queryFactory.selectFrom(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(search(searchCondition.name()))
                .orderBy(member.memberId.desc())
                .fetch();
        List<FindResponseMemberListModel> listDTO = new ArrayList<>();
        for (Member member1 : list) {
            listDTO.add(new FindResponseMemberListModel(
                    member1.getMemberId(),
                    member1.getName(),
                    member1.getAddress()
            ));
        }
        JPAQuery<Long> count = queryFactory.select(Wildcard.count).from(member);
        return PageableExecutionUtils.getPage(listDTO, pageable, count::fetchOne);
    }

    private BooleanExpression search(String searchVal) {
        return searchVal != null ? member.name.contains(searchVal) : null;
    }
}
