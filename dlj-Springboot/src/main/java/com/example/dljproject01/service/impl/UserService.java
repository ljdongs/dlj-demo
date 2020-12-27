package com.example.dljproject01.service.impl;

import com.example.dljproject01.dto.User;

import java.util.List;

/**
 * 用户信息服务接口
 */
public interface UserService {
    /**
     * 新增用户信息
     *
     * @param user er(String q);*/
     User addUser(User user);

    List<User> getAllUser(String q);
    /**
     * 根据用户ID查询用户信息
     *
     * @param user
     * @return
     */
    User getUserByUserId(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    User modifyUser(User user);

    /**
     * 删除用户信息
     *
     * @param user
     */
    void removeUser(User user);

    /**
     * 删除用户信息
     *
     * @param id
     */
    String removeUserById(String id);
}
