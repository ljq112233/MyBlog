package com.blog.service;

import com.blog.pojo.Tag;

import java.util.List;

/**
 * @author Lu Jianqiang
 * @date 2021/7/27 21 17
 * discription
 */
public interface TagService {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getBlogTag();

    List<Tag> getTagByString(String text);   //从字符串中获取tag集合

    int updateTag(Tag tag);

    int deleteTag(Long id);
}
