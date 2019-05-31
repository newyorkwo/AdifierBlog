package com.karu.web.admin;

import com.karu.domain.Type;
import com.karu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @description: create Type Controller
 * @author: StevenWu
 * @create: 2019-05-23 11:03
 **/
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort={"id"},direction = Sort.Direction.DESC)
                        Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t=typeService.saveType(type);

        if (t==null){
            attributes.addFlashAttribute("message","操作失敗");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }
}
