package com.blog.service;

import com.blog.pojo.Type;

import java.util.List;

/**
 * @author Lu Jianqiang
 * @date 2021/7/27 20 47
 * discription
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    int updateType(Type type);

    int deleteType(Long id);
}
