package com.dongyoung.company.company.model;

public record FindResponseCompanyListModel(
        Long companyId,
        String name,
        String address
) {
}
