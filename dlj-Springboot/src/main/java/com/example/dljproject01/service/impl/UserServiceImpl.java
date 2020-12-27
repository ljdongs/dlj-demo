package com.example.dljproject01.service.impl;

import com.example.dljproject01.dao.UserDao;
import com.example.dljproject01.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
/**
 * 用户信息服务实现
 */
@Service
public class UserServiceImpl implements UserService {
    //它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
    //@Autowired的使用来消除 set ，get方法
    // 容器启动，为对象赋值的时候，遇到@Autowired注解，会用后置处理器机制，来创建属性的实例，
    // 然后再利用反射机制，将实例化好的属性，赋值给对象上，这就是@Autowired的原理
    @Autowired
    private UserDao userDao;

    @Override
    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        userDao.insert(user);
        return user;
    }

    @Override
    public List<User> getAllUser(String q) {
        List<User> users;
        if (StringUtils.isEmpty(q)){
            users=userDao.queryAllUser();
        }else {
            users=userDao.queryAllUserByQ(q);
        }
        return users;
    }

    @Override
    public User getUserByUserId(User user) {

        return userDao.queryUserByUserId(user);
    }

    @Override
    public User modifyUser(User user) {
        userDao.updateUser(user);
        return user;
    }

    @Override
    public void removeUser(User user) {

        userDao.deleteUser(user);
    }

    @Override
    public String removeUserById(String id) {
        userDao.deleteUserById(id);
        return id;
    }
}
