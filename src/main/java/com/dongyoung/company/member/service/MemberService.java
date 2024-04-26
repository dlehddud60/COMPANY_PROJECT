package com.dongyoung.company.member.service;

import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.model.FindRequestMemberUpdateModel;
import com.dongyoung.company.member.model.FindResponseMemberListModel;
import com.dongyoung.company.member.model.FindResponseMemberModel;
import org.springframework.ui.Model;

import java.util.List;

public interface MemberService {
    void save(FindRequestMemberInsertModel insertModel);

    List<FindResponseMemberListModel> list(Model model);

    FindResponseMemberModel findByMemberId(Long memberId);

    void update(FindRequestMemberUpdateModel findRequestMemberUpdateModel);
}
