package com.dongyoung.company.model;

public record FindRequestCompanyUpdateModel(
        Long companyId,
        String name,
        String address
) {
}
