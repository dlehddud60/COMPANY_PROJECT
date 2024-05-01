package com.dongyoung.company.company.repository.impl;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.SearchCondition;
import com.dongyoung.company.company.model.mapper.CompanyMapper;
import com.dongyoung.company.company.repository.CompanyQueryRepository;
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

import static com.dongyoung.company.company.entity.QCompany.company;

@Repository
@RequiredArgsConstructor
public class CompanyQueryRepositoryImpl implements CompanyQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final CompanyMapper companyMapper;

    @Override
    public Page<FindResponseCompanyListModel> findAllByQueryDsl(SearchCondition search, Pageable pageable) {
        List<Company> list = queryFactory.selectFrom(company)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(search(search.name()))
                .orderBy(company.companyId.desc())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(Wildcard.count).from(company);
        return PageableExecutionUtils.getPage(list.stream().map(companyMapper::toCompanyListModel).collect(Collectors.toList()), pageable, count::fetchOne);
    }

    private BooleanExpression search(String searchValue) {
        return searchValue != null ? company.addName.name.contains(searchValue) : null;
    }
}
