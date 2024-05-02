package com.dongyoung.company.info.model.mapper;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.FindResponseInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfoMapper {

    @Mapping(source = "info.member", target = "listModel")
    @Mapping(source = "info.member.addName.name", target = "listModel.name")
    @Mapping(source = "info.member.addName.address", target = "listModel.address")
    @Mapping(source = "info.member.company.addName.name", target = "listModel.companyAndMemberListModel.name")
    @Mapping(source = "info.member.company.addName.address", target = "listModel.companyAndMemberListModel.address")
    FindResponseInfoListModel toInfoListModel(Info info);
    FindResponseInfoModel toInfoModel(Info info);
}
