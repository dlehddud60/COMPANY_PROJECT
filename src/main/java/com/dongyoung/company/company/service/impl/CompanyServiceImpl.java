package com.dongyoung.company.company.service.impl;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.repository.CompanyRepository;
import com.dongyoung.company.company.service.CompanyService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final EntityManager em;
    private final CompanyRepository companyRepository;

    @Override
    public void save(FindRequestMemberInsertModel insertModel) {
        Company company = Company.builder()
                .name(insertModel.name())
                .address(insertModel.address())
                .build();
        em.persist(company);
    }

    @Override
    public List<FindResponseCompanyListModel> findAll() {
        List<Company> list = companyRepository.findAll();
        List<FindResponseCompanyListModel> listModels = new ArrayList<>();
        for (Company company : list) {
            listModels.add(new FindResponseCompanyListModel(
                    company.getCompanyId(),
                    company.getName(),
                    company.getAddress()));
        }
        return listModels;
    }

    @Override
    public FindResponseCompanyModel findByCompanyId(Long companyId) {
        Company company = em.find(Company.class, companyId);
        FindResponseCompanyModel companyModel = new FindResponseCompanyModel(
                company.getCompanyId(),
                company.getName(),
                company.getAddress());
        return companyModel;
    }
}
