package com.dongyoung.company.info.service;

import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.FindResponseInfoModel;

import java.util.List;

public interface InfoService {
    void save(FindRequestInfoInsertModel insertModel);

    List<FindResponseInfoListModel> findAll();

    FindResponseInfoModel findbyInfoId(Long infoId);
}
