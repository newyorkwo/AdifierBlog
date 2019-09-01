package com.karu.dao;

import com.karu.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description create common repository
 * @AuthorName StevenWu
 * @CreateDateTime 2019-07-27-16:08
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List <Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
