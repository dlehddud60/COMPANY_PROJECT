package com.dongyoung.company.company.model;

public record FindResponseCompanyAndMemberListModel(
        Long companyId,
        String name,
        String address
) {
}
