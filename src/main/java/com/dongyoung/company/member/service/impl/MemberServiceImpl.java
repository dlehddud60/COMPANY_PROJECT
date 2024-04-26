package com.dongyoung.company.member.service.impl;

import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.model.FindRequestMemberUpdateModel;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.FindResponseMemberModel;
import com.dongyoung.company.member.repository.MemberRepository;
import com.dongyoung.company.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<FindResponseMemberListModel> list(Model model) {
        List<Member> list = memberRepository.findAll();
        List<FindResponseMemberListModel> listDTO = new ArrayList<>();
        for (Member member : list) {
            listDTO.add(new FindResponseMemberListModel(member.getMemberId(),member.getName(),member.getAddress()));
        }
        return listDTO;
    }

    @Override
    public FindResponseMemberModel findByMemberId(Long memberId) {
        Member member = em.find(Member.class, memberId);
        FindResponseMemberModel memberModel = new FindResponseMemberModel(
                member.getMemberId(),
                member.getName(),
                member.getAddress());
        return memberModel;
    }

    @Override
    public void update(FindRequestMemberUpdateModel findRequestMemberUpdateModel) {
        Member member = em.find(Member.class, findRequestMemberUpdateModel.memberId());
        member.setName(findRequestMemberUpdateModel.name());
        member.setAddress(findRequestMemberUpdateModel.address());
        em.flush();
    }
}
