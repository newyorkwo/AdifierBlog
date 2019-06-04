package com.karu.service;

import com.karu.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 描述:
 * create Blog Service Implements method
 *
 * @author StevenWu
 * @create 2019-06-04-22:53
 */
public class BlogServiceImpl implements BlogService{


    @Override
    public Blog getBlog(Long id) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return null;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return null;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        return null;
    }

    @Override
    public void deleteBlog(Long id) {

    }
}
