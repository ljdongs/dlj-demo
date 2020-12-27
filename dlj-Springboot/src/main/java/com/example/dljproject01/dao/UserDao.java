package com.example.dljproject01.dao;

import com.example.dljproject01.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 *
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 */

@Mapper
@Repository   //那么在spring的扫包机制下，也会生成这个dao的bean，注入你serviceImpl中的@Autowired
public interface UserDao {
    /**
     * 新增用户信息updateUser
     *
     * @param user
     */
    void insert(User user);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 关键字获取所有用户信息
     *
     * @return
     */
    List<User> queryAllUserByQ(String q);

    /**
     * 根据用户ID查询用户信息
     *
     * @param user
     * @return
     */
    User queryUserByUserId(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     * 删除用户信息
     *
     * @param id
     */
    void deleteUserById(String id);
}
