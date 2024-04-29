package com.dongyoung.company.company.service;

import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;

import java.util.List;

public interface CompanyService {
    void save(FindRequestMemberInsertModel insertModel);

    List<FindResponseCompanyListModel> findAll();

    FindResponseCompanyModel findByCompanyId(Long companyId);
}
