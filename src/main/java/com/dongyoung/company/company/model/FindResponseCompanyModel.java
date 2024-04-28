package com.dongyoung.company.company.model;

import jakarta.persistence.Column;

public record FindResponseCompanyModel(
        Long companyId,
        String name,
        String address
) {
}
