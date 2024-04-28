package com.dongyoung.company.model;

public record FindResponseCompanyListModel(
        Long companyId,
        String name,
        String address
) {
}
