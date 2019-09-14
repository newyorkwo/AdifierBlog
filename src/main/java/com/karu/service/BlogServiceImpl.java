package com.karu.service;

import com.karu.dao.BlogRepository;
import com.karu.domain.Blog;
import com.karu.domain.Type;
import com.karu.util.MarkdownUtils;
import com.karu.util.MyBeanUtils;
import com.karu.vo.BlogQuery;
import com.karu.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 描述:
 * create Blog Service Implements method
 *
 * @author StevenWu
 * @create 2019-06-04-22:53
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {

        return blogRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog=blogRepository.findById(id).orElseThrow(NotFoundException::new);
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        String content=b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId() !=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");//關聯查詢
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable=PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }



    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b=blogRepository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogRepository.findGroupYear();
        Map<String, List<Blog>> map=new HashMap<>();
        for(String year: years){
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
