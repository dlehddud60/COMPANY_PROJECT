package com.dongyoung.company.company.entity;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Embedded
    private AddName addName;

    @OneToMany(mappedBy = "company")
    private final List<Member> members = new ArrayList<>();
}
