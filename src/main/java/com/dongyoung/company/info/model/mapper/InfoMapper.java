package com.dongyoung.company.info.model.mapper;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.FindResponseInfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfoMapper {

    FindResponseInfoListModel toInfoListModel(Info info);
    FindResponseInfoModel toInfoModel(Info info);
}
