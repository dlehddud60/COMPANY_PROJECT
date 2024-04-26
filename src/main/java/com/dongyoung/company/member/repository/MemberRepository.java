package com.dongyoung.company.member.repository;

import com.dongyoung.company.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
