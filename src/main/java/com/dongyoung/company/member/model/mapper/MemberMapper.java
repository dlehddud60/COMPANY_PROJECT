package com.dongyoung.company.member.model.mapper;

import com.dongyoung.company.info.model.FindResponseInfoAndMemberListModel;
import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindResponseMemberAndCompanyModel;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.FindResponseMemberModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    @Mapping(source = "info",target = "infoAndMemberListModel")
    @Mapping(source = "company.addName.name",target = "companyAndMemberListModel.name")
    @Mapping(source = "company.addName.address",target = "companyAndMemberListModel.address")
    FindResponseMemberListModel toMemberListModel(Member member);


    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    @Mapping(source = "info",target = "infoAndMemberModel")
    @Mapping(source = "company.addName.name",target = "companyAndMemberModel.name")
    @Mapping(source = "company.addName.address",target = "companyAndMemberModel.address")
    FindResponseMemberModel toMemberModel(Member member);

}
