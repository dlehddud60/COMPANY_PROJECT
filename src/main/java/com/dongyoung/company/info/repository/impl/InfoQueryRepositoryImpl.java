package com.dongyoung.company.info.repository.impl;

import com.dongyoung.company.company.entity.QCompany;
import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.SearchCondition;
import com.dongyoung.company.info.model.mapper.InfoMapper;
import com.dongyoung.company.info.repository.InfoQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongyoung.company.company.entity.QCompany.*;
import static com.dongyoung.company.info.entity.QInfo.info;
import static com.dongyoung.company.member.entity.QMember.*;

@Log4j2
@Repository
@RequiredArgsConstructor
public class InfoQueryRepositoryImpl implements InfoQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final InfoMapper infoMapper;

    @Override
    public Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        List<Info> list = queryFactory.selectFrom(info)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .join(info.member,member)
                .join(member.company, company)
                .where(
                        searchCareer(searchCondition.career()),
                        searchMemberName(searchCondition.memberName()),
                        searchCompanyName(searchCondition.companyName())
                      )
                .orderBy(info.infoId.desc())
                .fetch();
        JPAQuery<Long> count = queryFactory.select(Wildcard.count).from(info);
        return PageableExecutionUtils.getPage(list.stream().map(infoMapper::toInfoListModel).collect(Collectors.toList()), pageable, count::fetchOne);
    }

    private BooleanExpression searchCareer(String searchValue) {
        return searchValue != null ? info.career.contains(searchValue) : null;
    }

    private BooleanExpression searchMemberName(String searchValue) {
        return searchValue != null ? info.member.addName.name.contains(searchValue) : null;
    }
    private BooleanExpression searchCompanyName(String searchValue) {
        return searchValue != null ? info.member.addName.name.contains(searchValue) : null;
    }
}
