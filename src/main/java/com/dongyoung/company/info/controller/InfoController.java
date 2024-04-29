package com.dongyoung.company.info.controller;

import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.service.InfoService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/write")
    public String write() {
        return "/info/write";
    }
    @PostMapping("/save")
    public void save(@ModelAttribute FindRequestInfoInsertModel insertModel) {
        infoService.save(insertModel);
    }
}
