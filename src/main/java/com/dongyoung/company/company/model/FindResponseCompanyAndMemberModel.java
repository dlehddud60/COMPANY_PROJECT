package com.dongyoung.company.company.model;

public record FindResponseCompanyAndMemberModel(
        Long companyId,
        String name,
        String address
) {
}
