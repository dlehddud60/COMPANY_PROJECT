package com.dongyoung.company.company.repository;

import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyQueryRepository {
    Page<FindResponseCompanyListModel> findAllByQueryDsl(SearchCondition search, Pageable pageable);

}
