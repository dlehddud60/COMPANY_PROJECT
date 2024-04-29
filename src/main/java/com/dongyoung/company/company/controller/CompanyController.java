package com.dongyoung.company.company.controller;

import com.dongyoung.company.company.model.FindRequestCompanyUpdateModel;
import com.dongyoung.company.company.service.CompanyService;
import com.dongyoung.company.member.model.FindRequestMemberInsertModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(@ModelAttribute FindRequestMemberInsertModel insertModel) {
        companyService.save(insertModel);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list",companyService.findAll());
        return "/company/list";
    }

    @GetMapping("/find/{companyId}")
    public String find(@PathVariable Long companyId,Model model) {
        model.addAttribute("info",companyService.findByCompanyId(companyId));
        return "/company/view";
    }

    @GetMapping("/update/{companyId}")
    public String update(Model model,@PathVariable Long companyId) {
        model.addAttribute("info",companyService.findByCompanyId(companyId));
        return "/company/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FindRequestCompanyUpdateModel updateModel) {
        companyService.update(updateModel);
        return "redirect:/company/find/" + updateModel.companyId();
    }
}
