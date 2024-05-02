package com.dongyoung.company.info.model;

import com.dongyoung.company.member.model.FindResponseMemberAndInfoListModel;
import com.dongyoung.company.member.model.FindResponseMemberListModel;

public record FindResponseInfoListModel(
        Long infoId,
        String career,
        String salary,
        FindResponseMemberAndInfoListModel listModel
) {
}
