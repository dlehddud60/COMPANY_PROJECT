package com.dongyoung.company.info.service.impl;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.repository.InfoRepository;
import com.dongyoung.company.info.service.InfoService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
