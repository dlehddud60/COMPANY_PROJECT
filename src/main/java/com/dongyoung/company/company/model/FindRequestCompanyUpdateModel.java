package com.dongyoung.company.company.model;

public record FindRequestCompanyUpdateModel(
        Long companyId,
        String name,
        String address
) {
}
