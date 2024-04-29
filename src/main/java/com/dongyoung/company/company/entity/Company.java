package com.dongyoung.company.company.entity;

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

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "company")
    private final List<Member> members = new ArrayList<>();
}
