package com.dongyoung.company.member.model;

import com.dongyoung.company.company.model.FindResponseCompanyAndMemberListModel;

public record FindResponseMemberAndInfoListModel(
        Long memberId,
        String name,
        String address,
        FindResponseCompanyAndMemberListModel companyAndMemberListModel

) {
}
