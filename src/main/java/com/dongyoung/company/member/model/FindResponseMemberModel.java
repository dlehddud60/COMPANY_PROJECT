package com.dongyoung.company.member.model;

public record FindResponseMemberModel(
        Long memberId,
        String name,
        String address
) {
}
