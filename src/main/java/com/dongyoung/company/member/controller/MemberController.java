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

    @GetMapping("/write")
    public String write() {
        return "/member/write";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FindRequestMemberInsertModel insertModel) {
        memberService.save(insertModel);
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String list(Model model, @ModelAttribute SearchCondition searchCondition, @PageableDefault(page = 0, size = 10) Pageable pageable) {
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


    @GetMapping("/update/{memberId}")
    public String update(@PathVariable(name = "memberId") Long memberId, Model model) {
        model.addAttribute("info", memberService.findByMemberId(memberId));
        return "/member/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FindRequestMemberUpdateModel findRequestMemberUpdateModel) {
        memberService.update(findRequestMemberUpdateModel);
        return "redirect:/member/find/" + findRequestMemberUpdateModel.memberId();
    }

    @GetMapping("/delete/{memberId}")
    public String delete(@PathVariable(name = "memberId") Long memberId) {
        memberService.delete(memberId);
        return "redirect:/member/list";
    }

}
