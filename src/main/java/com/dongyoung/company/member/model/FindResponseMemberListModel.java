package com.dongyoung.company.member.model;

public record FindResponseMemberListModel(
        Long memberId,
        String name,
        String address
) {
}
