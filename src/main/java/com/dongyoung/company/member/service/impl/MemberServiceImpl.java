package com.dongyoung.company.member.service.impl;

import com.dongyoung.company.common.entity.AddName;
import com.dongyoung.company.member.entity.Member;
import com.dongyoung.company.member.model.*;
import com.dongyoung.company.member.model.mapper.MemberMapper;
import com.dongyoung.company.member.repository.MemberQueryRepository;
import com.dongyoung.company.member.repository.MemberRepository;
import com.dongyoung.company.member.service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final MemberMapper memberMapper;

    @Override
    public List<FindResponseMemberListModel> list(Model model) {
        return memberRepository.findAll().stream().map(memberMapper::toMemberListModel).collect(Collectors.toList());
    }

    @Override
    public Page<FindResponseMemberListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable) {
        return memberQueryRepository.findAllByQueryDsl(searchCondition, pageable);
    }

    @Override
    public FindResponseMemberModel findByMemberId(Long memberId) {
        return memberMapper.toMemberModel(memberRepository.findByMemberId(memberId));
    }

}
