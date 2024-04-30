package com.dongyoung.company.info.model;

public record FindRequestInfoInsertModel(
        String career,
        String salary,
        String memberName,
        String memberAddress,
        String companyResult,
        Long companyId,
        String companyName,
        String companyAddress
) {
}
