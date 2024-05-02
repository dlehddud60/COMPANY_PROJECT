package com.dongyoung.company.info.model;

import com.dongyoung.company.member.model.FindResponseMemberAndInfoModel;

public record FindResponseInfoModel(
        Long infoId,
        String career,
        String salary,
        FindResponseMemberAndInfoModel memberModel

) {
}
