package com.blog.dao;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Lu Jianqiang
 * @date 2021/7/25 20 51
 * discription
 */
@Repository
@Mapper
public interface UserDao {
    User queryByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
