package com.dongyoung.company.info.service.impl;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.FindResponseInfoModel;
import com.dongyoung.company.info.repository.InfoRepository;
import com.dongyoung.company.info.service.InfoService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final EntityManager em;

    @Override
    public void save(FindRequestInfoInsertModel insertModel) {
        Info info = Info.builder()
                .career(insertModel.career())
                .salary(insertModel.salary())
                .build();
        em.persist(info);
        em.flush();
    }

    @Override
    public List<FindResponseInfoListModel> findAll() {
        List<Info> list = infoRepository.findAll();
        List<FindResponseInfoListModel> findAll = new ArrayList<>();
        for (Info info : list) {
            findAll.add(new FindResponseInfoListModel(
                    info.getInfoId(),
                    info.getCareer(),
                    info.getSalary()));
        }
        return findAll;
    }

    @Override
    public FindResponseInfoModel findbyInfoId(Long infoId) {
        Info info = em.find(Info.class, infoId);
        FindResponseInfoModel findbyInfoId = new FindResponseInfoModel(
                info.getInfoId(),
                info.getCareer(),
                info.getSalary());
        return findbyInfoId;
    }
}