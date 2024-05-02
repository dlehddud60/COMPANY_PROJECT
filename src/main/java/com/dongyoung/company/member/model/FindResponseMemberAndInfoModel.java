package com.dongyoung.company.member.model;

import com.dongyoung.company.company.model.FindResponseCompanyAndMemberModel;

public record FindResponseMemberAndInfoModel(
        Long memberId,
        String name,
        String address,
        FindResponseCompanyAndMemberModel companyModel
) {
}
