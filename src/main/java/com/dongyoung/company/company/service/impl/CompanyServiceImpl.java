package com.dongyoung.company.company.service.impl;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindRequestCompanyUpdateModel;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.model.SearchCondition;
import com.dongyoung.company.company.repository.CompanyQueryRepository;
import com.dongyoung.company.company.repository.CompanyRepository;
import com.dongyoung.company.company.service.CompanyService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CompanyQueryRepository companyQueryRepository;

    @Override
    public void save(FindRequestMemberInsertModel insertModel) {
        Company company = Company.builder().name(insertModel.name()).address(insertModel.address()).build();
        em.persist(company);
    }

    @Override
    public Page<FindResponseCompanyListModel> findAllByQueryDsl(SearchCondition search, Pageable pageable) {
        return companyQueryRepository.findAllByQueryDsl(search, pageable);
    }

    @Override
    public List<FindResponseCompanyListModel> findAll() {
        List<Company> list = companyRepository.findAll();
        List<FindResponseCompanyListModel> listModels = new ArrayList<>();
        for (Company company : list) {
            listModels.add(new FindResponseCompanyListModel(company.getCompanyId(), company.getName(), company.getAddress()));
        }
        return listModels;
    }

    @Override
    public FindResponseCompanyModel findByCompanyId(Long companyId) {
        Company company = em.find(Company.class, companyId);
        FindResponseCompanyModel companyModel = new FindResponseCompanyModel(company.getCompanyId(), company.getName(), company.getAddress());
        return companyModel;
    }

    @Override
    public void update(FindRequestCompanyUpdateModel updateModel) {
        Company company = em.find(Company.class, updateModel.companyId());
        company.setName(updateModel.name());
        company.setAddress(updateModel.address());
        em.flush();
    }

    @Override
    public void delete(Long companyId) {
        Company company = em.find(Company.class, companyId);
        em.remove(company);
    }
}
