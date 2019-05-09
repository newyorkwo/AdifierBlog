package com.adi.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: create index controller
 * @author: StevenWu
 * @create: 2019-05-07 16:08
 **/
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        //int i=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("博客找不到");
//        }
        //System.out.println("-------Index page------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "adifierBlog";
    }
}
