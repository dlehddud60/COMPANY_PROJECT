package com.dongyoung.company.member.entity;

import com.dongyoung.company.company.entity.Company;
import com.dongyoung.company.info.entity.Info;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne(mappedBy = "member",fetch = FetchType.LAZY)
    private Info info;

    @JoinColumn(name = "COMPANY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

}
