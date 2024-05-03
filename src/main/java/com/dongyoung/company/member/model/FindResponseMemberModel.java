package com.dongyoung.company.member.model;

import com.dongyoung.company.company.model.FindResponseCompanyAndMemberListModel;
import com.dongyoung.company.company.model.FindResponseCompanyAndMemberModel;
import com.dongyoung.company.info.model.FindResponseInfoAndMemberModel;

public record FindResponseMemberModel(
        Long memberId,
        String name,
        String address,
        FindResponseInfoAndMemberModel infoAndMemberModel,
        FindResponseCompanyAndMemberModel companyAndMemberModel
) {
}
