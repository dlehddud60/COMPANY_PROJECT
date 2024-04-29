package com.dongyoung.company.info.entity;

import com.dongyoung.company.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INFO")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Info {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "INFO_ID")
    private Long infoId;

    @Column(name = "CAREER")
    private String career;

    @Column(name = "SALARY")
    private String salary;

    @JoinColumn(name = "MEMBER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
