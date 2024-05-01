package com.dongyoung.company.member.model.mapper;

import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.FindResponseMemberModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    FindResponseMemberListModel toMemberListModel(Member member);

    @Mapping(source = "addName.name",target = "name")
    @Mapping(source = "addName.address",target = "address")
    FindResponseMemberModel toMemberModel(Member member);
}
