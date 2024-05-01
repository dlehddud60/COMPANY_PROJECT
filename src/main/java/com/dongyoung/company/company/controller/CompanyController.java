package com.dongyoung.company.company.controller;

import com.dongyoung.company.company.model.FindRequestCompanyUpdateModel;
import com.dongyoung.company.company.model.FindResponseCompanyModel;
import com.dongyoung.company.company.model.SearchCondition;
import com.dongyoung.company.company.service.CompanyService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/write")
    public String write() {
        return "/company/write";
    }

    @PostMapping("/save")
    public String save(FindRequestMemberInsertModel insertModel) {
        companyService.save(insertModel);
        return "redirect:/company/list";
    }

    @GetMapping("/list")
    public String list(Model model, SearchCondition search, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        model.addAttribute("list", companyService.findAllByQueryDsl(search, pageable));
        model.addAttribute("maxPage", 10);
        return "/company/list";
    }

    @GetMapping("/find/{companyId}")
    public String find(@PathVariable Long companyId, Model model) {
        FindResponseCompanyModel byCompanyId = companyService.findByCompanyId(companyId);
        log.info("===============byCompanyId=============={}",byCompanyId);
        model.addAttribute("info", byCompanyId);
        return "/company/view";
    }

    @GetMapping("/update/{companyId}")
    public String update(Model model, @PathVariable Long companyId) {
        model.addAttribute("info", companyService.findByCompanyId(companyId));
        return "/company/update";
    }

    @PostMapping("/update")
    public String update(FindRequestCompanyUpdateModel updateModel) {
        companyService.update(updateModel);
        return "redirect:/company/find/" + updateModel.companyId();
    }

    @GetMapping("/delete/{companyId}")
    public String delete(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return "redirect:/company/list";
    }
}
