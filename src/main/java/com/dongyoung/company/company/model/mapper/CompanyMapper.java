package com.dongyoung.company.company.model.mapper;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.company.model.FindResponseCompanyListModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    FindResponseCompanyListModel toCompanyListModel(Company company);

    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    FindResponseCompanyModel toCompanyModel(Company company);
}
