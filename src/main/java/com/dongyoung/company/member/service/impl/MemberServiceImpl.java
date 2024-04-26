package com.dongyoung.company.member.service.impl;

import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.repository.MemberRepository;
import com.dongyoung.company.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Override
    public void save(FindRequestMemberInsertModel insertModel) {
        Member member = Member.builder()
                .name(insertModel.name())
                .address(insertModel.address())
                .build();
        em.persist(member);
        em.flush();
    }
}
