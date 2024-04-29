package com.dongyoung.company.info.model;

public record FindRequestInfoUpdateModel(
        Long infoId,
        String career,
        String salary
) {
}
