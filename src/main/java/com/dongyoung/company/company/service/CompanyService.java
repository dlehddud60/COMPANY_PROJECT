package com.dongyoung.company.company.service;

import com.dongyoung.company.company.model.FindRequestCompanyUpdateModel;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.model.SearchCondition;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    void save(FindRequestMemberInsertModel insertModel);

    List<FindResponseCompanyListModel> findAll();

    FindResponseCompanyModel findByCompanyId(Long companyId);

    void update(FindRequestCompanyUpdateModel updateModel);

    void delete(Long companyId);

    Page<FindResponseCompanyListModel> findAllByQueryDsl(SearchCondition search, Pageable pageable);
}
