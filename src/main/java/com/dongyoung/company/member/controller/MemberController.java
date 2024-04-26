package com.dongyoung.company.member.controller;

import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import com.dongyoung.company.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/write")
    public String  write() {
        return "/member/write";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute FindRequestMemberInsertModel insertModel) {
        log.info("===============save==============");
        memberService.save(insertModel);
        return "redirect:/member/write";
    }

}
