package com.blog.service.impl;/**
 * @author Lu Jianqiang
 * @date 2021/7/26 21 52
 * discription
 */

import com.blog.dao.BlogDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogAndTag;
import com.blog.pojo.Tag;
import com.blog.service.BlogService;
import com.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/26 21:52
 * @Version 1.0
 */

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDao;



    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public List<Blog> getIndexBlog() {

        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);
        HashMap<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getAllBlog().size();
    }
    //新增博客
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
//        保存
        blogDao.saveBlog(blog);
//        获取自增id
        Long id = blog.getId();
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }
    //编辑博客
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }
}
