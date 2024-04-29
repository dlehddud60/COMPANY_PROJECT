package com.dongyoung.company.info.controller;

import com.dongyoung.company.info.model.FindRequestInfoInsertModel;
import com.dongyoung.company.info.model.FindRequestInfoUpdateModel;
import com.dongyoung.company.info.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(@ModelAttribute FindRequestInfoInsertModel insertModel) {
        infoService.save(insertModel);
        return "redirect:/info/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list",infoService.findAll());
        return "/info/list";
    }

    @GetMapping("/find/{infoId}")
    public String find(@PathVariable Long infoId, Model model) {
        model.addAttribute("info",infoService.findbyInfoId(infoId));
        return "info/view";
    }

    @GetMapping("/update/{infoId}")
    public String update(@PathVariable Long infoId, Model model) {
        model.addAttribute("info",infoService.findbyInfoId(infoId));
        return "/info/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FindRequestInfoUpdateModel updateModel) {
        infoService.update(updateModel);
        return "redirect:/info/find/" + updateModel.infoId();
    }

    @GetMapping("/delete/{infoId}")
    public String delete(@PathVariable Long infoId) {
        infoService.delete(infoId);
        return "redirect:/info/list";
    }
}
