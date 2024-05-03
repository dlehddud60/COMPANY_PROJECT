package com.dongyoung.company.company.model;

import com.dongyoung.company.member.model.FindResponseMemberAndCompanyModel;
import jakarta.persistence.Column;

import java.util.List;

public record FindResponseCompanyModel(
        Long companyId,
        String name,
        String address,
        List<FindResponseMemberAndCompanyModel> members
) {
}
