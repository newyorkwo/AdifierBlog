package com.karu.service;

import com.karu.dao.CommentRepository;
import com.karu.domain.Comment;
import com.karu.web.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description create comment service implement methods
 * @AuthorName StevenWu
 * @CreateDateTime 2019-07-27-08:55
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort=new Sort(Sort.Direction.DESC, "createTime");
        return commentRepository.findByBlogId(blogId, sort);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.findById(parentCommentId).orElseThrow(NotFoundException::new));
        }else{
            comment.setParentComment(null);
        }
        return commentRepository.save(comment);
    }
}
