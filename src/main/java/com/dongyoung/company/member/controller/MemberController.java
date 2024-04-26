package com.dongyoung.company.member.controller;

import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.model.FindRequestMemberUpdateModel;
import com.dongyoung.company.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.PerformanceSensitive;
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
        log.info("===============save==============");
        memberService.save(insertModel);
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", memberService.list(model));
        return "/member/list";
    }

    @GetMapping("/find/{memberId}")
    public String find(@PathVariable Long memberId, Model model) {
        model.addAttribute("info", memberService.findByMemberId(memberId));
        return "/member/view";
    }


    @GetMapping("/update/{memberId}")
    public String update(@PathVariable Long memberId, Model model) {
        model.addAttribute("info", memberService.findByMemberId(memberId));
        return "/member/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FindRequestMemberUpdateModel findRequestMemberUpdateModel) {
        memberService.update(findRequestMemberUpdateModel);
        return "redirect:/member/find/" + findRequestMemberUpdateModel.memberId();
    }

    @GetMapping("/delete/{memberId}")
    public String  delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/member/list";
    }

}
