package com.dongyoung.company.info.repository;

import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InfoQueryRepository {
    Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable);
}
