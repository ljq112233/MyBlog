package com.blog.service.impl;/**
 * @author Lu Jianqiang
 * @date 2021/7/27 21 08
 * discription
 */

import com.blog.dao.TypeDao;
import com.blog.pojo.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/27 21:08
 * @Version 1.0
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    //事务注解
    @Transactional
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    @Transactional
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
    }

    @Override
    @Transactional
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    @Transactional
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
