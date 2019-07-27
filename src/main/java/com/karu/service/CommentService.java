package com.karu.service;

import com.karu.domain.Comment;

import java.util.List;

/**
 * Create by Steven Wu on 2019-07-27
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
