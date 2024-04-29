package com.dongyoung.company.info.repository.impl;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.SearchCondition;
import com.dongyoung.company.info.repository.InfoQueryRepository;
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

import static com.dongyoung.company.info.entity.QInfo.info;

@Repository
@RequiredArgsConstructor
public class InfoQueryRepositoryImpl implements InfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        List<Info> list = queryFactory.selectFrom(info)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(search(searchCondition.career()))
                .orderBy(info.infoId.desc())
                .fetch();
        List<FindResponseInfoListModel> findAllByQueryDsl = new ArrayList<>();
        for (Info info1 : list) {
            findAllByQueryDsl.add(new FindResponseInfoListModel(
                    info1.getInfoId(),
                    info1.getCareer(),
                    info1.getSalary()));
        }

        JPAQuery<Long> count = queryFactory.select(Wildcard.count).from(info);
        return PageableExecutionUtils.getPage(findAllByQueryDsl, pageable, count::fetchOne);
    }

    private BooleanExpression search(String searchValue) {
        return searchValue != null ? info.career.contains(searchValue) : null;
    }
}
