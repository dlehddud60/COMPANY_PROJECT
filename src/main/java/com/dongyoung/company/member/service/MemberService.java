package com.dongyoung.company.member.service;

import com.dongyoung.company.member.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;

public interface MemberService {
    void save(FindRequestMemberInsertModel insertModel);

    List<FindResponseMemberListModel> list(Model model);

    FindResponseMemberModel findByMemberId(Long memberId);

    void update(FindRequestMemberUpdateModel findRequestMemberUpdateModel);

    void delete(Long memberId);

    Page<FindResponseMemberListModel> findAllByQueryDsl(SearchCondition searchCondition, Pageable pageable);
}
