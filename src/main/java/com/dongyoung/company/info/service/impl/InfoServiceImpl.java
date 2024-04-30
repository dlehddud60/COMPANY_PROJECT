package com.dongyoung.company.info.service.impl;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.repository.CompanyRepository;
import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.*;
import com.dongyoung.company.info.repository.InfoQueryRepository;
import com.dongyoung.company.info.repository.InfoRepository;
import com.dongyoung.company.info.service.InfoService;
import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final InfoQueryRepository queryRepository;
    private final CompanyRepository companyRepository;
    private final EntityManager em;


    @Override
    public void save(FindRequestInfoInsertModel insertModel) {

        String companyResult = insertModel.companyResult();
        switch (companyResult) {
           case "Y" -> {
               Company companyById = companyRepository.findByCompanyId(insertModel.companyId());
               Member member = Member.builder()
                       .name(insertModel.memberName())
                       .address(insertModel.memberAddress())
                       .company(companyById)
                       .build();
               em.persist(member);
               Info info = Info.builder()
                       .career(insertModel.career())
                       .salary(insertModel.salary())
                       .member(member)
                       .build();
               em.persist(info);
           }
           case "N" -> {
               Company company = Company.builder()
                       .name(insertModel.companyName())
                       .address(insertModel.companyAddress())
                       .build();
               em.persist(company);
               Member member = Member.builder()
                       .name(insertModel.memberName())
                       .address(insertModel.memberAddress())
                       .company(company)
                       .build();
               em.persist(member);
               Info info = Info.builder()
                       .career(insertModel.career())
                       .salary(insertModel.salary())
                       .member(member)
                       .build();
               em.persist(info);
           }
        }
    }

    @Override
    public Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        return queryRepository.findAllByQueryDsl(searchCondition, pageable);
    }

    @Override
    public List<FindResponseInfoListModel> findAll() {
        List<Info> list = infoRepository.findAll();
        List<FindResponseInfoListModel> findAll = new ArrayList<>();
        for (Info info : list) {
            findAll.add(new FindResponseInfoListModel(info.getInfoId(), info.getCareer(), info.getSalary()));
        }
        return findAll;
    }

    @Override
    public FindResponseInfoModel findbyInfoId(Long infoId) {
        Info info = em.find(Info.class, infoId);
        FindResponseInfoModel findbyInfoId = new FindResponseInfoModel(info.getInfoId(), info.getCareer(), info.getSalary());
        return findbyInfoId;
    }

    @Override
    public void update(FindRequestInfoUpdateModel updateModel) {
        Info info = em.find(Info.class, updateModel.infoId());
        info.setCareer(updateModel.career());
        info.setSalary(updateModel.salary());
        em.flush();
    }

    @Override
    public void delete(Long infoId) {
        Info info = em.find(Info.class, infoId);
        em.remove(info);
    }

    @Override
    public List<FindResponseCompanyListModel> findAllByCompany() {
        List<Company> all = companyRepository.findAll();
        List<FindResponseCompanyListModel> list = new ArrayList<>();
        for (Company company : all) {
            list.add(new FindResponseCompanyListModel(
                    company.getCompanyId(),
                    company.getName(),
                    company.getAddress()));
        }
        return list;
    }
}
