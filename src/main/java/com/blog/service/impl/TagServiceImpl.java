package com.blog.service.impl;/**
 * @author Lu Jianqiang
 * @date 2021/7/27 21 18
 * discription
 */

import com.blog.dao.TagDao;
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/27 21:18
 * @Version 1.0
 */

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagDao tagDao;


    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagDao.getBlogTag();
    }

    @Override
    //从tagIds字符串中获取id，根据id获取tag集合
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = converToList(text);
        for (Long l : longs) {
            tags.add(tagDao.getTag(l));
        }
        return tags;
    }
    //把前端的tagIds字符串转换为list集合
    public List<Long> converToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for (String s : idarray) {
                list.add(new Long(s));
            }
        }
        return list;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }
}
