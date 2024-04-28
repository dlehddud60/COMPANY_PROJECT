package com.dongyoung.company.company.service.impl;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.repository.CompanyRepository;
import com.dongyoung.company.company.service.CompanyService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
