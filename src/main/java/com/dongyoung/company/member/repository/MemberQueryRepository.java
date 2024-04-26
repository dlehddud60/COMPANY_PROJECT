package com.dongyoung.company.member.repository;

import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryRepository {
    Page<FindResponseMemberListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable);
}
