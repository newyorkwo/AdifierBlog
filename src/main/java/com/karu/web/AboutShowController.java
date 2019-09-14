package com.karu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description create aboutMe
 * @AuthorName StevenWu
 * @CreateDateTime 2019-09-14-6:57 下午
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
