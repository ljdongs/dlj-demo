package com.example.dljproject01;

import com.example.dljproject01.dto.User;
import com.example.dljproject01.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DljProject01ApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserId("wangzherongyao");
        user.setUserName("腾讯王者荣耀");
        user.setAge(20);
        user.setAddress("成都高新区");
        user.setPassword("123235025");
        userService.addUser(user);
    }


}
