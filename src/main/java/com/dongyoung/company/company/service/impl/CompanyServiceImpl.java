package com.dongyoung.company.company.service.impl;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindRequestCompanyUpdateModel;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.model.SearchCondition;
import com.dongyoung.company.company.model.mapper.CompanyMapper;
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
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyQueryRepository companyQueryRepository;
    private final  CompanyMapper companyMapper;

    @Transactional
    @Override
    public void save(FindRequestMemberInsertModel insertModel) {
        Company company = Company.builder()
                .addName(AddName.builder()
                        .name(insertModel.name())
                        .address(insertModel.address())
                        .build())
                .build();
        companyRepository.save(company);
    }

    @Override
    public Page<FindResponseCompanyListModel> findAllByQueryDsl(SearchCondition search, Pageable pageable) {
        return companyQueryRepository.findAllByQueryDsl(search, pageable);
    }

    @Override
    public List<FindResponseCompanyListModel> findAll() {
        return companyRepository.findAll().stream().map(companyMapper::toCompanyListModel).collect(Collectors.toList());
    }

    @Override
    public FindResponseCompanyModel findByCompanyId(Long companyId) {
        return companyMapper.toCompanyModel(companyRepository.findByCompanyId(companyId));
    }

    @Transactional
    @Override
    public void update(FindRequestCompanyUpdateModel updateModel) {
        Company company = companyRepository.findByCompanyId(updateModel.companyId());
        company.getAddName().setName(updateModel.name());
        company.getAddName().setAddress(updateModel.address());
    }

    @Transactional
    @Override
    public void delete(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
