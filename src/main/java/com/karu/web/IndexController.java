package com.karu.web;


import com.karu.service.BlogService;
import com.karu.service.TagService;
import com.karu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: create index controller
 * @author: StevenWu
 * @create: 2019-05-07 16:08
 **/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort={"updateTime"}, direction= Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog() {
        return "blog";
    }
}
