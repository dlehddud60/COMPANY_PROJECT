package com.dongyoung.company.info.controller;

import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.model.FindRequestInfoUpdateModel;
import com.dongyoung.company.info.model.SearchCondition;
import com.dongyoung.company.info.service.InfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("companyList",infoService.findAllByCompany());
        return "/info/write";
    }

    @PostMapping("/save")
    public String save(FindRequestInfoInsertModel insertModel) {
        infoService.save(insertModel);
        return "redirect:/info/list";
    }

    @GetMapping("/list")
    public String list(Model model, SearchCondition searchCondition, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        model.addAttribute("list", infoService.findAllByQueryDsl(searchCondition, pageable));
        model.addAttribute("maxPage", 10);
        return "/info/list";
    }

    @GetMapping("/find/{infoId}")
    public String find(@PathVariable Long infoId, Model model) {
        model.addAttribute("info", infoService.findbyInfoId(infoId));
        return "info/view";
    }

    @GetMapping("/update/{infoId}")
    public String update(@PathVariable Long infoId, Model model) {
        model.addAttribute("info", infoService.findbyInfoId(infoId));
        model.addAttribute("companyList",infoService.findAllByCompany());
        return "/info/update";
    }

    @PostMapping("/update")
    public String update(FindRequestInfoUpdateModel updateModel) {
        infoService.update(updateModel);
        return "redirect:/info/find/" + updateModel.infoId();
    }

    @GetMapping("/delete/{infoId}")
    public String delete(@PathVariable Long infoId) {
        infoService.delete(infoId);
        return "redirect:/info/list";
    }
}
