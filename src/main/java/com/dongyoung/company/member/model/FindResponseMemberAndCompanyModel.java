package com.dongyoung.company.member.model;

import com.dongyoung.company.company.model.FindResponseCompanyAndMemberModel;
import com.dongyoung.company.info.model.FindResponseInfoAndMemberModel;

public record FindResponseMemberAndCompanyModel(
        Long memberId,
        String name,
        String address,
        FindResponseInfoAndMemberModel infoAndMemberModel
) {
}
