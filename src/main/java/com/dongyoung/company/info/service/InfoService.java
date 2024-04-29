package com.dongyoung.company.info.service;

import com.dongyoung.company.info.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InfoService {
    void save(FindRequestInfoInsertModel insertModel);

    List<FindResponseInfoListModel> findAll();

    FindResponseInfoModel findbyInfoId(Long infoId);

    void update(FindRequestInfoUpdateModel updateModel);

    void delete(Long infoId);

    Page<FindResponseInfoListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable);
}
