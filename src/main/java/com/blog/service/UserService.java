package com.blog.service;

import com.blog.pojo.User;

/**
 * @author Lu Jianqiang
 * @date 2021/7/25 20 58
 * discription
 */

public interface UserService {


    public User checkUser(String username,String password);
}
