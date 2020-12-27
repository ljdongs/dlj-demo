package com.example.dljproject01.controller;

import com.example.dljproject01.dto.User;
import com.example.dljproject01.service.impl.UserService;
import com.example.dljproject01.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//@Controller注解，表明了这个类是一个控制器类
//@RequestMapping注解是用来映射请求的，即指明处理器可以处理哪些URL请求，该注解既可以用在类上，也可以用在方法上
//要求方法返回的是json格式数据，而不是跳转页面，可以直接在类上标注@RestController
@RestController
@Api(tags = "用户信息管理")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/demo/v1/add")
    public Result add(@RequestBody User user) {
        return new Result<>(userService.addUser(user));
    }

    @PostMapping("/demo/v1/list")
    public Result list( @ApiParam @RequestParam String q) {
        return new Result<>(userService.getAllUser(q));
    }

    @PostMapping("/demo/v1/query_user_by_user_id")
    public User queryUserByUserId(@RequestBody User user) {
        return userService.getUserByUserId(user);
    }

    @PostMapping("/demo/v1/update_user_by_user_id")
    public Result updateUserByUserId(@RequestBody User user) {
        return new Result<>(userService.modifyUser(user));
    }

    @PostMapping("/demo/v1/delete_user_by_user_id")
    public void deleteUserByUserId(@RequestBody User user) {
        userService.removeUser(user);
    }

    @DeleteMapping("/demo/v1/delete_user_by_id")
    public String deleteByUserId(@RequestParam("id") String id) {
        return userService.removeUserById(id);
    }
//    @DeleteMapping("/demo/v1/delete_user_by_id")
//    public Result deleteByUserId(@RequestParam String id) {
//        return new Result<>(userService.removeUserById(id));
//    }

}
