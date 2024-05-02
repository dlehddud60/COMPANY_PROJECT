package com.dongyoung.company.info.model.mapper;

import com.dongyoung.company.info.entity.Info;
import com.dongyoung.company.info.model.FindResponseInfoListModel;
import com.dongyoung.company.info.model.FindResponseInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfoMapper {

    @Mapping(source = "info.member.addName.name", target = "listModel.name")
    @Mapping(source = "info.member.addName.address", target = "listModel.address")
    @Mapping(source = "info.member.company.addName.name", target = "listModel.companyAndMemberListModel.name")
    @Mapping(source = "info.member.company.addName.address", target = "listModel.companyAndMemberListModel.address")
    FindResponseInfoListModel toInfoListModel(Info info);

    @Mapping(source = "info.member.addName.name", target = "memberModel.name")
    @Mapping(source = "info.member.addName.address", target = "memberModel.address")
    @Mapping(source = "info.member.company.addName.name", target = "memberModel.companyModel.name")
    @Mapping(source = "info.member.company.addName.address", target = "memberModel.companyModel.address")
    FindResponseInfoModel toInfoModel(Info info);
}
