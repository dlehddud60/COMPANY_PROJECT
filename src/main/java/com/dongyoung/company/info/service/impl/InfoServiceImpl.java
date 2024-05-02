package com.dongyoung.company.info.service.impl;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.mapper.CompanyMapper;
import com.dongyoung.company.company.repository.CompanyRepository;
import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.*;
import com.dongyoung.company.info.model.mapper.InfoMapper;
import com.dongyoung.company.info.repository.InfoQueryRepository;
import com.dongyoung.company.info.repository.InfoRepository;
import com.dongyoung.company.info.service.InfoService;
import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final InfoQueryRepository queryRepository;
    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final InfoMapper infoMapper;
    private final CompanyMapper companyMapper;

    @Transactional
    @Override
    public void save(FindRequestInfoInsertModel insertModel) {

        String companyResult = insertModel.companyResult();
        switch (companyResult) {
            case "Y" -> {
                Company companyById = companyRepository.findByCompanyId(insertModel.companyId());
                DTOMappingMethod(insertModel, companyById);
            }
            case "N" -> {
                Company company = Company.builder()
                        .addName(AddName.builder()
                                .name(insertModel.companyName())
                                .address(insertModel.companyAddress())
                                .build())
                        .build();
                companyRepository.save(company);
                DTOMappingMethod(insertModel, company);
            }
        }
    }

    private void DTOMappingMethod(FindRequestInfoInsertModel insertModel, Company company) {
        Member member = Member.builder()
                .addName(AddName.builder()
                        .name(insertModel.memberName())
                        .address(insertModel.memberAddress())
                        .build())
                .company(company)
                .build();
        memberRepository.save(member);
        Info info = Info.builder()
                .career(insertModel.career())
                .salary(insertModel.salary())
                .member(member)
                .build();
        infoRepository.save(info);
    }

    @Override
    public Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        return queryRepository.findAllByQueryDsl(searchCondition, pageable);
    }

    @Override
    public List<FindResponseInfoListModel> findAll() {
        return infoRepository.findAll().stream().map(infoMapper::toInfoListModel).collect(Collectors.toList());
    }

    @Override
    public FindResponseInfoModel findbyInfoId(Long infoId) {
        return infoMapper.toInfoModel(infoRepository.findByInfoId(infoId));
    }

    @Transactional
    @Override
    public void update(FindRequestInfoUpdateModel updateModel) {
        String result = updateModel.companyResult();
        Info info = infoRepository.findByInfoId(updateModel.infoId());
        switch (result) {
            case "Y" -> {
                info.getMember().setCompany(companyRepository.findByCompanyId(updateModel.companyId()));
                info.getMember().getAddName().setName(updateModel.memberName());
                info.getMember().getAddName().setAddress(updateModel.memberAddress());
                info.setCareer(updateModel.career());
                info.setSalary(updateModel.salary());
            }
            case "N" -> {
                Company company = Company.builder()
                        .addName(AddName.builder()
                                .name(updateModel.companyName())
                                .address(updateModel.companyAddress())
                                .build())
                                .build();
                companyRepository.save(company);
                info.getMember().setCompany(company);
                info.getMember().getAddName().setName(updateModel.memberName());
                info.getMember().getAddName().setAddress(updateModel.memberAddress());
                info.setCareer(updateModel.career());
                info.setSalary(updateModel.salary());
            }
        }
    }

    @Transactional
    @Override
    public void delete(Long infoId) {
        log.info("==================infoId=================={}",infoId);
        log.info("================delete============={}",memberRepository.findByMemberId(infoId).getMemberId());
        infoRepository.deleteById(infoId);
        memberRepository.deleteById(infoId);
    }

    @Override
    public List<FindResponseCompanyListModel> findAllByCompany() {
        return companyRepository.findAll().stream().map(companyMapper::toCompanyListModel).collect(Collectors.toList());
    }
}
