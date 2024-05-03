package com.dongyoung.company.member.model;

import com.dongyoung.company.company.model.FindResponseCompanyAndMemberListModel;
import com.dongyoung.company.info.model.FindResponseInfoAndMemberListModel;

public record FindResponseMemberListModel(
        Long memberId,
        String name,
        String address,
        FindResponseInfoAndMemberListModel infoAndMemberListModel,
        FindResponseCompanyAndMemberListModel companyAndMemberListModel
) {
}
