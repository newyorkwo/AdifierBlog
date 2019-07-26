package com.karu.web.admin;

import com.karu.domain.Comment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 描述:
 * create Comment controller
 *
 * @author StevenWu
 * @create 2019-07-26-22:54
 */
public class CommentController {

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments","");
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){

        return "redirect:/comments/" + comment.getBlog().getId();
    }
}
