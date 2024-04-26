package com.dongyoung.company.member.model;

public record FindRequestMemberUpdateModel(
        Long memberId,
        String name,
        String address
) {
}
