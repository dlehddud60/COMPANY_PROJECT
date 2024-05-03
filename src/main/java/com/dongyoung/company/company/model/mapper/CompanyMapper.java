package com.dongyoung.company.company.model.mapper;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.info.model.FindResponseInfoAndMemberModel;
import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindResponseMemberAndCompanyModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    default List<FindResponseMemberAndCompanyModel> toFindMemberAndCompanyModel(Company company) {
        return company.getMembers().stream().map(member -> new FindResponseMemberAndCompanyModel(
                member.getMemberId(),
                member.getAddName().getName(),
                member.getAddName().getAddress(),
                new FindResponseInfoAndMemberModel(
                        member.getInfo().getInfoId(),
                        member.getInfo().getCareer(),
                        member.getInfo().getSalary()))).toList();
    }

    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    FindResponseCompanyListModel toCompanyListModel(Company company);

    @Mappings({
            @Mapping(source = "addName.name", target = "name"),
            @Mapping(source = "addName.address", target = "address"),
            @Mapping(expression = "java(toFindMemberAndCompanyModel(company))",target = "members")
    })
    FindResponseCompanyModel toCompanyModel(Company company);

}
