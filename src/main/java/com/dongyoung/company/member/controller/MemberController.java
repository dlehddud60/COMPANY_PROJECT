package com.dongyoung.company.member.controller;

import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.model.FindRequestMemberUpdateModel;
import com.dongyoung.company.member.model.SearchCondition;
import com.dongyoung.company.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, SearchCondition searchCondition, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        model.addAttribute("list", memberService.findAllByQueryDsl(searchCondition, pageable));
        model.addAttribute("maxPage", 10);
        model.addAttribute("name", searchCondition.name());
        return "/member/list";
    }

    @GetMapping("/find/{memberId}")
    public String find(@PathVariable(name = "memberId") Long memberId, Model model) {
        model.addAttribute("info", memberService.findByMemberId(memberId));
        return "/member/view";
    }

}
