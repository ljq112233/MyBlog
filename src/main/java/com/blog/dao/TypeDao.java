package com.blog.dao;/**
 * @author Lu Jianqiang
 * @date 2021/7/27 20 37
 * discription
 */

import com.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TypeDao
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/27 20:37
 * @Version 1.0
 */

@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getBlogType();

    int updateType(Type type);//首页右侧展示type对应的博客数量

    int deleteType(Long id);


}
