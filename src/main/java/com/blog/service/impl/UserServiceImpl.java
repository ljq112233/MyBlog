package com.blog.service.impl;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 20 57
 * discription
 */

import com.blog.dao.UserDao;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/25 20:57
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

@Autowired
    private UserDao userDao;


    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username,MD5Utils.code(password));
        return user;
    }
}
