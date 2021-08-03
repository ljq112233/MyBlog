package com.blog.dao;/**
 * @author Lu Jianqiang
 * @date 2021/7/27 21 11
 * discription
 */

import com.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TagDao
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/27 21:11
 * @Version 1.0
 */

@Mapper
@Repository
public interface TagDao {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getBlogTag();  //首页展示博客标签

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
